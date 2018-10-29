package com.yizhipin.goods.data.repository

import com.yizhipin.base.data.net.RetrofitFactoryGet
import com.yizhipin.base.data.protocol.BasePagingResp
import com.yizhipin.generalizecenter.data.response.GeneralizeCollect
import com.yizhipin.goods.data.api.GeneralizeApi
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by ${XiLei} on 2018/7/27.
 */
class GeneralizeRepository @Inject constructor() {

    fun getGenBiddingList(map: MutableMap<String, String>): Observable<BasePagingResp<MutableList<GeneralizeCollect>>> {
        return RetrofitFactoryGet().create(GeneralizeApi::class.java).getGenBiddingList(map["currentPage"]!!, map["bidding"]!!)
    }


}