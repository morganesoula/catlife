package com.ms.catlife.feature_main.presentation.cats

import com.ms.catlife.core.data.entities.Cat
import com.ms.catlife.feature_add_cat.domain.util.CatOrder
import com.ms.catlife.feature_add_cat.domain.util.OrderType

data class CatsState(
    val cats: List<Cat> = emptyList(),
    val catsOrder: CatOrder = CatOrder.Name(OrderType.Ascending)
)
