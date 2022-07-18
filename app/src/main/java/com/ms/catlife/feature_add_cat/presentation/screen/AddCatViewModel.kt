package com.ms.catlife.feature_add_cat.presentation.screen

import android.annotation.SuppressLint
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.net.toUri
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ms.catlife.feature_add_cat.domain.model.AddCatFormState
import com.ms.catlife.feature_add_cat.domain.use_cases.cat_characteristics.ValidationUseCases
import com.ms.catlife.feature_add_cat.domain.use_cases.crud.CatUseCases
import com.ms.catlife.feature_add_cat.presentation.AddCatDateEvent
import com.ms.catlife.feature_add_cat.presentation.AddCatFormEvent
import com.ms.catlife.feature_main.domain.model.Cat
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@SuppressLint("StaticFieldLeak")
@HiltViewModel
class AddCatViewModel @Inject constructor(
    private val catUseCases: CatUseCases,
    private val validationUseCases: ValidationUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var state by mutableStateOf(AddCatFormState())

    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    private var currentCatId: Int? = null

    init {
        savedStateHandle.get<Int>("catId")?.let { catId ->
            if (catId != -1) {
                viewModelScope.launch {
                    catUseCases.getCatUseCase(catId)?.also { cat ->
                        currentCatId = cat.id
                        initCat(cat)
                    }
                }
            }
        }
    }

    fun onEvent(
        event: AddCatFormEvent
    ) {
        when (event) {
            is AddCatFormEvent.CatGenderChanged -> {
                state = state.copy(catGender = event.gender)
            }

            is AddCatFormEvent.OnCatProfilePicturePathChanged -> {
                state = state.copy(catPictureUri = event.catProfilePicturePath)
            }

            is AddCatFormEvent.CatNameChanged -> {
                state = state.copy(catName = event.catName)
            }

            is AddCatFormEvent.CatNeuteredChanged -> {
                state = state.copy(catNeutered = event.neutered)
            }

            is AddCatFormEvent.CatWeightChanged -> {
                state = state.copy(weight = event.weight)
            }

            is AddCatFormEvent.CatCoatChanged -> {
                state = state.copy(catCoat = event.coat)
            }

            is AddCatFormEvent.CatRaceChanged -> {
                state = state.copy(catRace = event.race)
            }

            is AddCatFormEvent.CatDiseasesChanged -> {
                state = state.copy(catDiseases = event.diseases)
            }

            is AddCatFormEvent.Submit -> {
                submitData()
            }

            is AddCatFormEvent.OnBackPressed -> run {
                state = AddCatFormState()
            }
        }
    }

    fun onDateEvent(dateEvent: AddCatDateEvent, date: Long) {
        state = when (dateEvent) {
            is AddCatDateEvent.OnBirthdayChanged -> {
                state.copy(catBirthdate = date)
            }

            is AddCatDateEvent.OnDewormingChanged -> {
                state.copy(catDewormingDate = date)
            }

            is AddCatDateEvent.OnFleaChanged -> {
                state.copy(catFleaDate = date)
            }

            is AddCatDateEvent.OnVaccineChanged -> {
                state.copy(catVaccineDate = date)
            }
        }
    }

    private fun submitData() {
        val catNameResult = validationUseCases.validateCatNameUseCase.execute(state.catName)
        val catWeightResult = validationUseCases.validateCatWeightUseCase.execute(state.weight)
        val catCoatResult = validationUseCases.validateCatCoatUseCase.execute(state.catCoat)
        val catBirthdateResult = validationUseCases.validateCatBirthdateUseCase.execute(state.catBirthdate)
        val catVaccineResult = validationUseCases.validateCatVaccineDateUseCase.execute(state.catVaccineDate)
        val catFleaResult = validationUseCases.validateCatFleaDateUseCase.execute(state.catFleaDate)
        val catDewormingResult = validationUseCases.validateCatDewormingDateUseCase.execute(state.catDewormingDate)

        val hasError = listOf(
            catNameResult,
            catWeightResult,
            catCoatResult,
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
            catUseCases.insertCatUseCase(
                Cat(
                    id = currentCatId,
                    name = state.catName,
                    gender = state.catGender,
                    neutered = state.catNeutered,
                    profilePicturePath = state.catPictureUri.toString(),
                    birthdate = state.catBirthdate,
                    weight = state.weight.toFloat(),
                    race = state.catRace,
                    coat = state.catCoat,
                    diseases = state.catDiseases,
                    fleaDate = state.catFleaDate,
                    dewormingDate = state.catDewormingDate,
                    vaccineDate = state.catVaccineDate
                )
            )
            validationEventChannel.send(ValidationEvent.Success)
        }
    }

    sealed class ValidationEvent {
        object Success : ValidationEvent()
    }

    private fun initCat(cat: Cat) {
        state = state
            .copy(catName = cat.name)
            .copy(catGender = cat.gender)
            .copy(catNeutered = cat.neutered)
            .copy(catRace = cat.race)
            .copy(catBirthdate = cat.birthdate)
            .copy(weight = cat.weight.toString())
            .copy(catCoat = cat.coat)
            .copy(catVaccineDate = cat.vaccineDate ?: 0)
            .copy(catFleaDate = cat.fleaDate ?: 0)
            .copy(catDiseases = cat.diseases ?: "")
            .copy(catDewormingDate = cat.dewormingDate ?: 0)
            .copy(catPictureUri = cat.profilePicturePath?.toUri())
    }
}