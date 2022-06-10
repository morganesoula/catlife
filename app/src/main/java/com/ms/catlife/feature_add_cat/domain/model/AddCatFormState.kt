package com.ms.catlife.feature_add_cat.domain.model

data class AddCatFormState(
    val catPictureUri: String = "",
    val catGender: String = "",
    val catName: String = "",
    val catNameError: String? = null,
    val catBirthdate: Long = 0L,
    val catBirthdateError: String? = null,
    val catRace: String = "",
    val catRaceError: String? = null,
    val catVaccineDate: Long = 0L,
    val catVaccineDateError: String? = null,
    val catFleaDate: Long = 0L,
    val catFleaDateError: String? = null,
    val catDewormingDate: Long = 0L,
    val catDewormingDateError: String? = null,
    val weight: String = "",
    val weightError: String? = null,
    val catCoat: String = "",
    val catCoatError: String? = null,
    val catDiseases: String = "",
    val catDiseasesError: String? = null
)
