package com.ms.catlife.domain.repository

import androidx.annotation.WorkerThread
import com.ms.catlife.data.db.CatDAO
import com.ms.catlife.domain.model.Cat
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CatRepositoryImpl @Inject constructor(private val catDAO: CatDAO) : CatRepository {

    @WorkerThread
    override suspend fun insertCat(cat: Cat) = catDAO.insertCat(cat)

    @WorkerThread
    override suspend fun deleteCat(name: String) = catDAO.deleteCat(name)

    override suspend fun getAllCats(): Flow<Cat>? = catDAO.getAllCats()
}