package com.ms.catlife.presentation.add_cat_form

data class AddCatFormState(
    val catPictureUri: String = "",
    val catName: String = "",
    val catNameError: String? = null,
    val weight: Double = 0.0,
    val weightError: String? = null
)
