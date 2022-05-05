package com.ms.catlife.presentation.add_cat_form

sealed class AddCatFormEvent {

    data class CatPictureChanged(val pictureUri: String) : AddCatFormEvent()
    data class CatNameChanged(val catName: String) : AddCatFormEvent()
    data class CatWeightChanged(val weight: Double) : AddCatFormEvent()

    object Submit: AddCatFormEvent()
}