package com.ms.catlife.feature_add_cat.domain.repository

import com.ms.catlife.feature_main.domain.model.Cat
import kotlinx.coroutines.flow.Flow

interface CatRepository {

    fun getCats(): Flow<List<Cat>>

    suspend fun getCatById(id: Int): Cat?

    suspend fun insertCat(cat: Cat)

    suspend fun deleteCat(cat: Cat)
}