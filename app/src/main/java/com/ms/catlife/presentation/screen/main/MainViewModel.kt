package com.ms.catlife.presentation.screen.main

import androidx.lifecycle.ViewModel
import com.ms.catlife.domain.model.Cat
import com.ms.catlife.domain.use_case.CatUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val catUseCases: CatUseCases) : ViewModel() {

    //val allCats = catUseCases.getAllCatsUseCase

    val allCats = arrayListOf(
        Cat(0, "Femelle", "Douffy", weight = 5, race = "Siamois", coat = "Teal", birthdate = "14"),
        Cat(1, "Femelle", "Kokonut", weight = 4, race = "Européen", coat = "Noire et blanche", birthdate = "4"),
        Cat(2, "Femelle", "Chichi", weight = 4, race = "Siamois", coat = "Marbre", birthdate = "4")
    )
}