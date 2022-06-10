package com.ms.catlife.feature_add_cat.presentation

sealed class AddCatDateEvent {
    data class onBirthdayChanged(val birthdate: Long) : AddCatDateEvent()
    data class onVaccineChanged(val vaccine: Long) : AddCatDateEvent()
    data class onDewormingChanged(val deworming: Long) : AddCatDateEvent()
    data class onFleaChanged(val flea: Long) : AddCatDateEvent()
}
