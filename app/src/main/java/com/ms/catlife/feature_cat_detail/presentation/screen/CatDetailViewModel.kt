package com.ms.catlife.feature_cat_detail.presentation.screen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ms.catlife.core.domain.use_cases.crud.CatUseCases
import com.ms.catlife.core.util.DateFormatter
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.ms.catlife.feature_cat_detail.domain.model.CatDetailState
import com.ms.catlife.feature_cat_detail.presentation.CatDetailEvent
import kotlinx.coroutines.launch

@HiltViewModel
class CatDetailViewModel @Inject constructor(private val catUseCases: CatUseCases) : ViewModel() {

    var state by mutableStateOf(CatDetailState())

    fun onEvent(catDetailEvent: CatDetailEvent) {
        when (catDetailEvent) {
            is CatDetailEvent.OnCatIdReceived -> {
                fetchCat(catId = catDetailEvent.catId)
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
            }
        }
    }
}

