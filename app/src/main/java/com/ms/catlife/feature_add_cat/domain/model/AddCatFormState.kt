package com.ms.catlife.feature_add_cat.domain.model

import com.ms.catlife.core.util.UiText

data class AddCatFormState(
    val catPictureUri: String = "",
    val catGender: String = "",
    val catName: String = "",
    val catNameError: UiText? = null,
    val catBirthdate: Long = 0L,
    val catBirthdateError: UiText? = null,
    val catNeutered: String = "",
    val catRace: String = "",
    val catVaccineDate: Long = 0L,
    val catVaccineDateError: UiText? = null,
    val catFleaDate: Long = 0L,
    val catFleaDateError: UiText? = null,
    val catDewormingDate: Long = 0L,
    val catDewormingDateError: UiText? = null,
    val weight: String = "",
    val weightError: UiText? = null,
    val catCoat: String = "",
    val catCoatError: UiText? = null,
    val catDiseases: String = "",
    val catDiseasesError: UiText? = null
)
