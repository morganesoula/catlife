package com.ms.catlife.data.repository

import com.ms.catlife.data.repository.dataSource.CatLocalDataSource
import com.ms.catlife.domain.model.Cat
import com.ms.catlife.domain.repository.CatRepository
import kotlinx.coroutines.flow.Flow

class CatRepositoryImpl(
    private val catLocalDataSource: CatLocalDataSource
) : CatRepository {

    override suspend fun insertCat(cat: Cat) = catLocalDataSource.insertCat(cat)
    override suspend fun deleteCat(name: String) = catLocalDataSource.deleteCat(name)
    override suspend fun getAllCats(): Flow<Cat>? = catLocalDataSource.getAllCats()
}