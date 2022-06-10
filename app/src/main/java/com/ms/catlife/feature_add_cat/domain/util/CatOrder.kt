package com.ms.catlife.feature_add_cat.domain.util

sealed class CatOrder(val orderType: OrderType) {
    class Name(orderType: OrderType) : CatOrder(orderType)
    class Age(orderType: OrderType) : CatOrder(orderType)

    fun copy(orderType: OrderType): CatOrder {
        return when (this) {
            is Name -> Name(orderType)
            is Age -> Age(orderType)
        }
    }
}
