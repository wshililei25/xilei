package com.yizhipin.goods.ui.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.yizhipin.goods.ui.fragment.EvaluateFragment
import com.yizhipin.goods.ui.fragment.GoodsFragment
import com.yizhipin.goods.ui.fragment.ReportFragment
import com.yizhipin.goods.ui.fragment.ShopDetailsFragment

/**
 * Created by ${XiLei} on 2018/9/22.
 */
class ShopVpAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    private var mList = mutableListOf("商品", "评价", "体验报告", "详情")

    override fun getItem(position: Int): Fragment {

        if (position == 0) {
            return GoodsFragment()
        }
        if (position == 1) {
            return EvaluateFragment()
        }
        if (position == 2) {
            return ReportFragment()
        }
        if (position == 3) {
            return ShopDetailsFragment()
        }
        return null!!
    }

    override fun getCount(): Int {
        return mList.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mList[position]
    }
}