package com.ms.catlife.feature_add_cat.domain.util

sealed class OrderType {
    object Ascending : OrderType()
    object Descending : OrderType()
}
