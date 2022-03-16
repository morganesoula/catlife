package com.ms.catlife.domain.repository

import com.ms.catlife.domain.model.Cat
import kotlinx.coroutines.flow.Flow

interface CatRepository {
    suspend fun insertCat(cat: Cat)
    suspend fun deleteCat(name: String)
    suspend fun getAllCats(): Flow<Cat>?
}