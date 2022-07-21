package com.ms.catlife.feature_cat_detail.data.repository

import com.ms.catlife.core.data.data_source.CatDAO
import com.ms.catlife.core.data.entities.Cat
import com.ms.catlife.feature_cat_detail.domain.repository.CatRepository

class CatRepositoryImpl(private val catDAO: CatDAO) : CatRepository {
    override suspend fun getCatById(id: Int): Cat? = catDAO.getCatById(id)
}