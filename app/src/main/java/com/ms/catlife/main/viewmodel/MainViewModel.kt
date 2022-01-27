package com.ms.catlife.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.ms.catlife.main.data.model.Cat
import com.ms.catlife.main.data.repository.CatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val catRepository: CatRepository) : ViewModel() {

    //val allCats: LiveData<List<Cat>>? = catRepository.allCats?.asLiveData()

    val allCats = arrayListOf(
        Cat(0, "Kiwi", 0, 8),
        Cat(1, "Douffy", 0, 13),
        Cat(2, "Kokonut", 0, 4),
        Cat(3, "Chichi", 0, 4)
    )
}