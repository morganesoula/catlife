package com.ms.catlife.feature_main.presentation.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ms.catlife.feature_add_cat.domain.use_cases.crud.CatUseCases
import com.ms.catlife.feature_add_cat.domain.util.CatOrder
import com.ms.catlife.feature_add_cat.domain.util.OrderType
import com.ms.catlife.feature_main.domain.model.Cat
import com.ms.catlife.feature_main.presentation.cats.CatsEvent
import com.ms.catlife.feature_main.presentation.cats.CatsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val catUseCases: CatUseCases) : ViewModel() {

    var state by mutableStateOf(CatsState())

    private var recentlyDeletedCat: Cat? = null

    private var getCatsJob: Job? = null

    init {
        getCats(CatOrder.Age(OrderType.Ascending))
    }

    fun onEvent(event: CatsEvent) {
        when (event) {
            is CatsEvent.Order -> {
                if (state.catsOrder::class == event.catOrder::class &&
                    state.catsOrder.orderType == event.catOrder.orderType
                ) {
                    return
                }
                getCats(catsOrder = event.catOrder)
            }
            is CatsEvent.RestoreCat -> {
                viewModelScope.launch {
                    catUseCases.insertCatUseCase(recentlyDeletedCat ?: return@launch)
                    recentlyDeletedCat = null
                }
            }
            is CatsEvent.DeleteCat -> {
                viewModelScope.launch {
                    catUseCases.deleteCatUseCase(event.cat)
                    recentlyDeletedCat = event.cat
                }
            }
        }
    }

    private fun getCats(catsOrder: CatOrder) {
        getCatsJob?.cancel()
        getCatsJob = catUseCases.getAllCatsUseCase(catsOrder)
            .onEach { cats ->
                state = state.copy(cats = cats, catsOrder = catsOrder)
            }
            .launchIn(viewModelScope)
    }
}