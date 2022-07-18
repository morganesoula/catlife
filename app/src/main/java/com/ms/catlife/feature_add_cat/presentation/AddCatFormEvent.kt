package com.ms.catlife.feature_add_cat.presentation

import android.net.Uri

sealed class AddCatFormEvent {

    data class OnCatProfilePicturePathChanged(val catProfilePicturePath: Uri) : AddCatFormEvent()
    data class CatGenderChanged(val gender: Boolean) : AddCatFormEvent()
    data class CatNeuteredChanged(val neutered: Boolean): AddCatFormEvent()
    data class CatNameChanged(val catName: String) : AddCatFormEvent()
    data class CatWeightChanged(val weight: String) : AddCatFormEvent()
    data class CatCoatChanged(val coat: String) : AddCatFormEvent()
    data class CatDiseasesChanged(val diseases: String) : AddCatFormEvent()
    data class CatRaceChanged(val race: String) : AddCatFormEvent()
    object OnBackPressed : AddCatFormEvent()

    object Submit : AddCatFormEvent()
}