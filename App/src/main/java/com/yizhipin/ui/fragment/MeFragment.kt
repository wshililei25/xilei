package com.yizhipin.ui.fragment

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yizhipin.R
import com.yizhipin.base.common.BaseConstant
import com.yizhipin.base.ext.loadUrl
import com.yizhipin.base.ext.onClick
import com.yizhipin.base.ui.fragment.BaseMvpFragment
import com.yizhipin.base.utils.AppPrefsUtils
import com.yizhipin.base.utils.GlideUtils
import com.yizhipin.ordercender.common.OrderConstant
import com.yizhipin.ordercender.common.OrderStatus
import com.yizhipin.ordercender.ui.activity.OrderActivity
import com.yizhipin.ordercender.ui.activity.ShipAddressActivity
import com.yizhipin.provider.common.ProviderConstant
import com.yizhipin.provider.common.afterLogin
import com.yizhipin.provider.common.isLogined
import com.yizhipin.ui.activity.SettingActivity
import com.yizhipin.usercenter.common.UserConstant
import com.yizhipin.usercenter.data.response.UserInfo
import com.yizhipin.usercenter.injection.component.DaggerMainComponent
import com.yizhipin.usercenter.injection.module.MianModule
import com.yizhipin.usercenter.presenter.UserInfoPresenter
import com.yizhipin.usercenter.presenter.view.UserInfoView
import com.yizhipin.usercenter.ui.activity.CommissionerApplyActivity
import com.yizhipin.usercenter.ui.activity.UserInfoActivity
import com.yizhipin.usercenter.ui.activity.WalletActivity
import kotlinx.android.synthetic.main.fragment_me.*
import org.jetbrains.anko.support.v4.startActivity
import q.rorbin.badgeview.QBadgeView

/**
 * Created by ${XiLei} on 2018/8/19.
 */
class MeFragment : BaseMvpFragment<UserInfoPresenter>(), UserInfoView, View.OnClickListener {

    private lateinit var mQBadgeView: QBadgeView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_me, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun injectComponent() {
        DaggerMainComponent.builder().activityComponent(mActivityComponent).mianModule(MianModule()).build().inject(this)
        mBasePresenter.mView = this
    }

    override fun onStart() {
        super.onStart()
        loadData()
    }

    private fun initView() {
        mQBadgeView = QBadgeView(activity)
        mUserIconIv.onClick(this)
        mUserNameTv.onClick(this)
        mSettingTv.onClick(this)
        mAddressTv.onClick(this)
        mWaitPayOrderTv.onClick(this)
        mWaitConfirmOrderTv.onClick(this)
//        mCompleteOrderTv.onClick(this)
        mAllOrderTv.onClick(this)
        mCommissionerTv.onClick(this)
        mWalletView.onClick(this)

        setCartBadge()
    }

    private fun loadData() {
        if (isLogined()) {
            mUserIconIv.loadUrl(AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_ICON))
            mCommissionerIv.visibility = View.VISIBLE
            mGradeIv.visibility = View.VISIBLE
            mNewView.visibility = View.VISIBLE
            credit.visibility = View.VISIBLE
            mCreditTv.visibility = View.VISIBLE
            mAnountTv.visibility = View.VISIBLE
            mCartCountTv.visibility = View.VISIBLE
            mBasePresenter.getUserInfo()
        } else {
            mUserIconIv.setImageResource(R.drawable.user)
            mUserNameTv.text = activity!!.getString(R.string.login)
            mCommissionerIv.visibility = View.GONE
            mGradeIv.visibility = View.GONE
            mNewView.visibility = View.GONE
            credit.visibility = View.GONE
            mCreditTv.visibility = View.GONE
            mAnountTv.visibility = View.GONE
            mCartCountTv.visibility = View.GONE
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.mUserIconIv, R.id.mUserNameTv -> {
                afterLogin {
                    startActivity<UserInfoActivity>(UserConstant.KEY_TO_USERINFO to true)
                }
            }
            R.id.mSettingTv -> {
                afterLogin {
                    startActivity<SettingActivity>()
                }
            }
            R.id.mAddressTv -> { //收货地址
                afterLogin {
                    startActivity<ShipAddressActivity>()
                }
            }
            R.id.mWaitPayOrderTv -> {
                afterLogin {
                    startActivity<OrderActivity>(OrderConstant.KEY_ORDER_STATUS to OrderStatus.ORDER_WAIT_PAY)
                }
            }
            R.id.mWaitConfirmOrderTv -> {
                afterLogin {
                    startActivity<OrderActivity>(OrderConstant.KEY_ORDER_STATUS to OrderStatus.ORDER_WAIT_CONFIRM)
                }
            }
            /*   R.id.mCompleteOrderTv -> {
                   afterLogin {
                       startActivity<OrderActivity>(OrderConstant.KEY_ORDER_STATUS to OrderStatus.ORDER_COMPLETED)
                   }
               }*/
            R.id.mAllOrderTv -> {
                afterLogin {
                    startActivity<OrderActivity>()
                }
            }
            R.id.mCommissionerTv -> { //申请专员
                afterLogin {
                    startActivity<CommissionerApplyActivity>()
                }
            }
            R.id.mWalletView -> { //钱包
                afterLogin {
                    startActivity<WalletActivity>()
                }
            }

        }
    }

    /**
     *  获取用户信息成功
     */
    override fun getUserResult(result: UserInfo) {

        mUserNameTv.text = if (result.nickname.isNullOrEmpty()) getString(R.string.app_name) else result.nickname
        mCreditTv.text = result.score
        mAnountTv.text = "￥${result.totalAmount}"
        if (result.commissioner) mCommissionerIv.visibility = View.VISIBLE else mCommissionerIv.visibility = View.GONE
        if (result.commissioner) mCommissionerTv.text = getString(R.string.commissioner) else mCommissionerTv.text = getString(R.string.apply_commissioner)
        GlideUtils.loadUrlImage(activity!!, BaseConstant.IMAGE_SERVICE_ADDRESS.plus(result.imgurl), mUserIconIv)

        when (result.level) {
            1 -> mGradeIv.setImageResource(R.drawable.grade1)
            2 -> mGradeIv.setImageResource(R.drawable.grade2)
            3 -> mGradeIv.setImageResource(R.drawable.grade3)
            4 -> mGradeIv.setImageResource(R.drawable.grade4)
            5 -> mGradeIv.setImageResource(R.drawable.grade5)
            6 -> mGradeIv.setImageResource(R.drawable.grade6)
            7 -> mGradeIv.setImageResource(R.drawable.grade7)
            8 -> mGradeIv.setImageResource(R.drawable.grade8)
            9 -> mGradeIv.setImageResource(R.drawable.grade9)
            10 -> mGradeIv.setImageResource(R.drawable.grade10)
        }
    }

    override fun onEditUserResult(result: UserInfo) {
    }

    private fun setCartBadge() {
        mQBadgeView.badgeGravity = Gravity.END or Gravity.TOP
        mQBadgeView.setGravityOffset(10f, 0f, true)
        mQBadgeView.setBadgeTextSize(8f, true)
        mQBadgeView.bindTarget(mWaitPayOrderTv).badgeNumber = 5
    }

}