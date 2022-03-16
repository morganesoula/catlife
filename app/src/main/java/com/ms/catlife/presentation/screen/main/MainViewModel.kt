package com.ms.catlife.presentation.screen.main

import androidx.lifecycle.ViewModel
import com.ms.catlife.domain.model.Cat
import com.ms.catlife.domain.repository.CatRepositoryImpl
import com.ms.catlife.domain.useCase.CatUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val catUseCases: CatUseCases) : ViewModel() {

    //val allCats = catUseCases.getAllCatsUseCase

    val allCats = arrayListOf(
        Cat(0, "Douffy", 0, 13),
        Cat(1, "Kokonut", 0, 4),
        Cat(2, "Chichi", 0, 4)
    )
}