package com.ms.catlife.main.data.repository

import com.ms.catlife.main.data.model.Cat

interface ICatRepository {
    suspend fun insertCat(cat: Cat)
    fun deleteCat(name: String)
}