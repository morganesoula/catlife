package com.ms.catlife.feature_add_cat.presentation

sealed class AddCatFormEvent {

    data class CatGenderChanged(val gender: String) : AddCatFormEvent()
    data class CatPictureChanged(val pictureUri: String) : AddCatFormEvent()
    data class CatNameChanged(val catName: String) : AddCatFormEvent()
    data class CatWeightChanged(val weight: String) : AddCatFormEvent()
    data class CatCoatChanged(val coat: String) : AddCatFormEvent()
    data class CatDiseasesChanged(val diseases: String) : AddCatFormEvent()
    data class CatRaceChanged(val race: String) : AddCatFormEvent()
    object OnBackPressed : AddCatFormEvent()

    object Submit : AddCatFormEvent()
}