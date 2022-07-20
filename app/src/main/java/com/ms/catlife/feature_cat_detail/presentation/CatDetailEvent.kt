package com.ms.catlife.feature_cat_detail.presentation

sealed class CatDetailEvent {
    data class OnCatIdReceived(val catId: Int) : CatDetailEvent()
}
