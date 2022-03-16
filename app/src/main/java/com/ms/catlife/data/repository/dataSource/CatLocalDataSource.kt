package com.ms.catlife.data.repository.dataSource

import com.ms.catlife.domain.model.Cat
import kotlinx.coroutines.flow.Flow

interface CatLocalDataSource {
    fun getAllCats(): Flow<Cat>?
    fun insertCat(cat: Cat)
    fun deleteCat(name: String)
}