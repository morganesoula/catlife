package com.ms.catlife.feature_cat_detail.presentation

sealed class CatDetailEvent {
    data class OnCatIdReceived(val catId: Int) : CatDetailEvent()
    data class OnCatEdition(val catId: Int) : CatDetailEvent()
    data class OnDeleteCustomDialogOpen(val openDialog: Boolean, val deleteData: Boolean, val catId: Int) :
        CatDetailEvent()
}
