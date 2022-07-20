package com.ms.catlife.feature_cat_detail.data.repository

import com.ms.catlife.core.data.data_source.CatDAO
import com.ms.catlife.feature_cat_detail.domain.repository.CatRepository
import com.ms.catlife.core.data.entities.Cat

class CatRepositoryImpl(private val catDAO: CatDAO) : CatRepository {
    override suspend fun getCatById(id: Int): Cat? = catDAO.getCatById(id)
}