package com.ms.catlife.feature_add_cat.data.repository

import com.ms.catlife.core.data.data_source.CatDAO
import com.ms.catlife.core.data.entities.Cat
import com.ms.catlife.feature_add_cat.domain.repository.CatRepository
import kotlinx.coroutines.flow.Flow

class CatRepositoryImpl(
    private val dao: CatDAO
) : CatRepository {

    override fun getCats(): Flow<List<Cat>> = dao.getAllCats()
    override suspend fun getCatById(id: Int): Cat? = dao.getCatById(id)
    override suspend fun insertCat(cat: Cat) = dao.insertCat(cat)
    override suspend fun deleteCat(catId: Int) = dao.deleteCat(catId)
}