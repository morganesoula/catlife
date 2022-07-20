package com.ms.catlife.feature_add_cat.data.repository

import com.ms.catlife.feature_add_cat.domain.repository.CatRepository
import com.ms.catlife.core.data.entities.Cat
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeCatRepository : CatRepository {

    private val cats = mutableListOf<Cat>()

    override fun getCats(): Flow<List<Cat>> = flow { emit(cats) }

    override suspend fun getCatById(id: Int): Cat? = cats.find { it.id == id }

    override suspend fun insertCat(cat: Cat) {
        cats.add(cat)
    }

    override suspend fun deleteCat(cat: Cat) {
        cats.remove(cat)
    }
}