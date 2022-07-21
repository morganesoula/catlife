package com.ms.catlife.feature_main.presentation.cats

import com.ms.catlife.feature_add_cat.domain.util.CatOrder

sealed class CatsEvent {
    data class Order(val catOrder: CatOrder) : CatsEvent()
}
