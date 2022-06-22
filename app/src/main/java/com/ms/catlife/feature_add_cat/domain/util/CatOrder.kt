package com.ms.catlife.feature_add_cat.domain.util

sealed class CatOrder(val orderType: OrderType) {
    class Name(orderType: OrderType) : CatOrder(orderType)
    class Age(orderType: OrderType) : CatOrder(orderType)
}
