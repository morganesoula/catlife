package com.ms.catlife.feature_cat_detail.domain.repository

import com.ms.catlife.core.data.entities.Cat

interface CatRepository {
    suspend fun getCatById(id: Int): Cat?
}