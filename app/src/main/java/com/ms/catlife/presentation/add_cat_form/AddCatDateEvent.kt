package com.ms.catlife.presentation.add_cat_form

sealed class AddCatDateEvent {
    data class onBirthdayChanged(val birthdate: Long) : AddCatDateEvent()
    data class onVaccineChanged(val vaccine: Long) : AddCatDateEvent()
    data class onDewormingChanged(val deworming: Long) : AddCatDateEvent()
    data class onFleaChanged(val flea: Long) : AddCatDateEvent()
}
