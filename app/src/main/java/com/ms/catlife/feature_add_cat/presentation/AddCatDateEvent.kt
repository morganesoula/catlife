package com.ms.catlife.feature_add_cat.presentation

sealed class AddCatDateEvent {
    data class OnBirthdayChanged(val birthdate: Long) : AddCatDateEvent()
    data class OnVaccineChanged(val vaccine: Long) : AddCatDateEvent()
    data class OnDewormingChanged(val deworming: Long) : AddCatDateEvent()
    data class OnFleaChanged(val flea: Long) : AddCatDateEvent()
}
