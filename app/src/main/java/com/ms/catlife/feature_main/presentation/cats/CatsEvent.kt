package com.ms.catlife.feature_main.presentation.cats

import com.ms.catlife.feature_add_cat.domain.util.CatOrder
import com.ms.catlife.feature_main.domain.model.Cat

sealed class CatsEvent {
    data class Order(val catOrder: CatOrder) : CatsEvent()
    data class DeleteCat(val cat: Cat) : CatsEvent()
    object RestoreCat : CatsEvent()
}
