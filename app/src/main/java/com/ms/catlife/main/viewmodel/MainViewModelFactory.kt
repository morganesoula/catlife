package com.ms.catlife.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ms.catlife.main.data.repository.CatRepository
import java.lang.IllegalArgumentException

class MainViewModelFactory(private val catRepository: CatRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(catRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}