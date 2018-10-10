package com.yizhipin.goods.presenter

import com.yizhipin.base.ext.execute
import com.yizhipin.base.presenter.BasePresenter
import com.yizhipin.base.rx.BaseSubscriber
import com.yizhipin.base.utils.AppPrefsUtils
import com.yizhipin.goods.common.GoodsConstant
import com.yizhipin.goods.data.response.Evaluate
import com.yizhipin.goods.data.response.Goods
import com.yizhipin.goods.data.response.Report
import com.yizhipin.goods.presenter.view.GoodsDetailView
import com.yizhipin.goods.service.impl.CartServiceImpl
import com.yizhipin.goods.service.impl.GoodsServiceImpl
import javax.inject.Inject

/*
    商品详情 Presenter
 */
class GoodsDetailPresenter @Inject constructor() : BasePresenter<GoodsDetailView>() {

    @Inject
    lateinit var mGoodsServiceImpl: GoodsServiceImpl
    @Inject
    lateinit var mCartServiceImpl: CartServiceImpl

    /*
        获取商品详情
     */
    fun getGoodsDetail(map :MutableMap<String,String>) {
        mView.showLoading()
        mGoodsServiceImpl.getGoodsDetail(map).execute(object : BaseSubscriber<Goods>(mView) {
            override fun onNext(t: Goods) {
                mView.onGetGoodsDetailSuccess(t)
            }
        }, mLifecycleProvider)

    }

    /*
        加入购物车
     */
    fun addCart(goodsId: Int, goodsDesc: String, goodsIcon: String, goodsPrice: Long,
                goodsCount: Int, goodsSku: String) {
        mView.showLoading()
        mCartServiceImpl.addCart(goodsId, goodsDesc, goodsIcon, goodsPrice,
                goodsCount, goodsSku).execute(object : BaseSubscriber<Int>(mView) {
            override fun onNext(t: Int) {
                AppPrefsUtils.putInt(GoodsConstant.SP_CART_SIZE, t)
                mView.onAddCartSuccess(t)
            }
        }, mLifecycleProvider)

    }

    /**
     * 最新评价
     */
    fun getEvaluateNew(map :MutableMap<String,String>) {

        mView.showLoading()
        mGoodsServiceImpl.getEvaluateNew(map).execute(object : BaseSubscriber<Evaluate>(mView) {
            override fun onNext(t: Evaluate) {
                mView.onGetEvaluateNewSuccess(t)
            }
        }, mLifecycleProvider)

    }

    /**
     * 最新体验报告
     */
    fun getReportNew(map :MutableMap<String,String>) {
        mView.showLoading()
        mGoodsServiceImpl.getReportNew(map).execute(object : BaseSubscriber<Report>(mView) {
            override fun onNext(t: Report) {
                mView.onGetReportNewSuccess(t)
            }
        }, mLifecycleProvider)

    }
}