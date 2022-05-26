package com.ms.catlife.presentation.screen.addCatForm

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ms.catlife.domain.model.Cat
import com.ms.catlife.domain.use_case.CatUseCases
import com.ms.catlife.domain.use_case.add_cat_form.ValidationUseCases
import com.ms.catlife.presentation.add_cat_form.AddCatDateEvent
import com.ms.catlife.presentation.add_cat_form.AddCatFormEvent
import com.ms.catlife.presentation.add_cat_form.AddCatFormState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@SuppressLint("StaticFieldLeak")
@HiltViewModel
class AddCatViewModel @Inject constructor(
    private val catUseCases: CatUseCases,
    private val validationUseCases: ValidationUseCases
) : ViewModel() {

    var state by mutableStateOf(AddCatFormState())

    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    fun onEvent(
        event: AddCatFormEvent
    ) {
        when (event) {
            is AddCatFormEvent.CatGenderChanged -> {
                state = state.copy(catGender = event.gender)
            }
            is AddCatFormEvent.CatPictureChanged -> {
                state = state.copy(catPictureUri = event.pictureUri)
            }
            is AddCatFormEvent.CatNameChanged -> {
                state = state.copy(catName = event.catName)
            }
            is AddCatFormEvent.CatWeightChanged -> {
                state = state.copy(weight = event.weight)
            }
            is AddCatFormEvent.CatCoatChanged -> {
                state = state.copy(catCoat = event.coat)
            }
            is AddCatFormEvent.CatDiseasesChanged -> {
                state = state.copy(catDiseases = event.diseases)
            }
            is AddCatFormEvent.Submit -> {
                submitData()
            }
        }
    }

    fun onDateEvent(dateEvent: AddCatDateEvent, date: Long) {
        state = when (dateEvent) {
            is AddCatDateEvent.onBirthdayChanged -> {
                state.copy(catBirthdate = date)
            }
            is AddCatDateEvent.onDewormingChanged -> {
                state.copy(catDewormingDate = date)
            }
            is AddCatDateEvent.onFleaChanged -> {
                state.copy(catFleaDate = date)
            }
            is AddCatDateEvent.onVaccineChanged -> {
                state.copy(catVaccineDate = date)
            }
        }
    }

    private fun submitData() {
        val catNameResult = validationUseCases.validateCatNameUseCase.execute(state.catName)
        val catWeightResult = validationUseCases.validateCatWeightUseCase.execute(state.weight)
        val catCoatResult = validationUseCases.validateCatCoatUseCase.execute(state.catCoat)
        val catDiseasesResult = validationUseCases.validateCatDiseasesUseCase.execute(state.catDiseases)
        val catBirthdateResult = validationUseCases.validateCatBirthdateUseCase.execute(state.catBirthdate)
        val catVaccineResult = validationUseCases.validateCatVaccineDateUseCase.execute(state.catVaccineDate)
        val catFleaResult = validationUseCases.validateCatFleaDateUseCase.execute(state.catFleaDate)
        val catDewormingResult = validationUseCases.validateCatDewormingDateUseCase.execute(state.catDewormingDate)

        val hasError = listOf(
            catNameResult,
            catWeightResult,
            catCoatResult,
            catDiseasesResult,
            catBirthdateResult,
            catVaccineResult,
            catFleaResult,
            catDewormingResult
        ).any { !it.successful }

        state = state.copy(
            catNameError = catNameResult.errorMessage,
            weightError = catWeightResult.errorMessage,
            catCoatError = catCoatResult.errorMessage,
            catBirthdateError = catBirthdateResult.errorMessage,
            catVaccineDateError = catVaccineResult.errorMessage,
            catFleaDateError = catFleaResult.errorMessage,
            catDewormingDateError = catDewormingResult.errorMessage
        )

        if (hasError) {
            return
        }

        viewModelScope.launch {
            validationEventChannel.send(ValidationEvent.Success)
        }
    }

    fun insertCat(cat: Cat) =
        viewModelScope.launch {
            catUseCases.insertCatUseCase.invoke(cat)
        }

    sealed class ValidationEvent {
        object Success : ValidationEvent()
    }
}