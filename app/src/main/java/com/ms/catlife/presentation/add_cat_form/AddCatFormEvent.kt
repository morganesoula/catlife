package com.ms.catlife.presentation.add_cat_form

sealed class AddCatFormEvent {

    data class CatGenderChanged(val gender: String) : AddCatFormEvent()
    data class CatPictureChanged(val pictureUri: String) : AddCatFormEvent()
    data class CatNameChanged(val catName: String) : AddCatFormEvent()
    data class CatWeightChanged(val weight: String) : AddCatFormEvent()
    data class CatCoatChanged(val coat: String) : AddCatFormEvent()
    data class CatDiseasesChanged(val diseases: String) : AddCatFormEvent()

    object Submit : AddCatFormEvent()
}