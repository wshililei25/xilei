package com.yizhipin.generalizecenter.presenter.view

import com.yizhipin.base.data.protocol.BasePagingResp
import com.yizhipin.base.presenter.view.BaseView
import com.yizhipin.generalizecenter.data.response.GeneralizeCollect

/**
 * Created by ${XiLei} on 2018/7/26.
 */
interface GeneralizeView : BaseView {
    fun onGetGoodsListSuccess(result: BasePagingResp<MutableList<GeneralizeCollect>>)
}