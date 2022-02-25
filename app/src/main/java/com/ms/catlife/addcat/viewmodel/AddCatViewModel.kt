package com.ms.catlife.addcat.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ms.catlife.main.data.model.Cat
import com.ms.catlife.main.data.repository.CatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddCatViewModel @Inject constructor(private val catRepository: CatRepository) : ViewModel() {

    //TODO Make it not nullable
    fun insert(cat: Cat?) = viewModelScope.launch {
        cat?.let { catRepository.insertCat(it) }
    }
}