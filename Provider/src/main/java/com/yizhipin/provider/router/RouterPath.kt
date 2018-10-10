package com.yizhipin.provider.router

/*
    模块路由 路径定义
 */
object RouterPath {
    //用户模块
    class UserCenter {
        companion object {
            const val PATH_LOGIN = "/userCenter/login"
        }
    }

    //订单模块
    class OrderCenter {
        companion object {
            const val PATH_ORDER_CONFIRM = "/orderCenter/confirm"
        }
    }

    //支付模块
    class PayCenter {
        companion object {
            const val PATH_PAY_RECHARGE = "/payCenter/recharge"
            const val PATH_PAY_WITHDRAW = "/payCenter/withraw"
        }
    }

    //消息模块
    class MessageCenter {
        companion object {
            const val PATH_MESSAGE_PUSH = "/message/push"
            const val PATH_MESSAGE_ORDER = "/messageCenter/order"
        }
    }
}