package com.ms.catlife.feature_cat_detail.presentation.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ms.catlife.core.domain.use_cases.crud.CatUseCases
import com.ms.catlife.core.util.ValidationEvent
import com.ms.catlife.feature_cat_detail.domain.model.CatDetailState
import com.ms.catlife.feature_cat_detail.presentation.CatDetailEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatDetailViewModel @Inject constructor(private val catUseCases: CatUseCases) : ViewModel() {

    var state by mutableStateOf(CatDetailState())

    private val validationEventChannel = Channel<ValidationEvent>()
    val validationsEvents = validationEventChannel.receiveAsFlow()

    fun onEvent(catDetailEvent: CatDetailEvent) {
        when (catDetailEvent) {
            is CatDetailEvent.OnCatIdReceived -> {
                fetchCat(catId = catDetailEvent.catId)
            }

            is CatDetailEvent.OnCatEdition -> {
                //TODO
            }

            is CatDetailEvent.OnDeleteCustomDialogOpen -> {
                if (catDetailEvent.deleteData) {
                    viewModelScope.launch {
                        catUseCases.deleteCatUseCase(catDetailEvent.catId)
                        validationEventChannel.send(ValidationEvent.Success)
                    }
                }

                state = state.copy(customDialogState = catDetailEvent.openDialog)
            }
        }
    }

    private fun fetchCat(catId: Int) {
        viewModelScope.launch {
            val catFetched = catUseCases.getCatUseCase.invoke(catId)

            catFetched?.let { catInDb ->
                state = state
                    .copy(catName = catInDb.name)
                    .copy(catProfilePath = catInDb.profilePicturePath)
                    .copy(catBirthdate = catInDb.birthdate)
                    .copy(neutered = catInDb.neutered)
                    .copy(lastVaccineDate = catInDb.vaccineDate)
                    .copy(lastFleaDate = catInDb.fleaDate)
                    .copy(lastDewormingDate = catInDb.dewormingDate)
                    .copy(race = catInDb.race)
                    .copy(fur = catInDb.coat)
                    .copy(weight = catInDb.weight)
                    .copy(diseases = catInDb.diseases)
            }
        }
    }
}

