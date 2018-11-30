package com.yizhipin.paycenter.ui.activity

import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.yizhipin.base.ui.activity.BaseActivity
import com.yizhipin.paycenter.R
import com.yizhipin.provider.router.RouterPath

/**
 * Created by ${XiLei} on 2018/9/24.
 *
 * 充值
 */
@Route(path = RouterPath.PayCenter.PATH_PAY_RECHARGE)
class RechargeActivity : BaseActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recharge)

        initView()
    }

    private fun initView() {
//        mRechargeTv.onClick(this)
    }


    override fun onClick(v: View) {
        when (v.id) {
//            R.id.mRechargeTv ->
        }
    }


}