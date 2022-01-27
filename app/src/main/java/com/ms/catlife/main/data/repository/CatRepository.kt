package com.ms.catlife.main.data.repository

import androidx.annotation.WorkerThread
import com.ms.catlife.main.data.dao.ICatDAO
import com.ms.catlife.main.data.model.Cat
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CatRepository @Inject constructor(private val catDAO: ICatDAO) : ICatRepository {
    val allCats: Flow<List<Cat>>? = catDAO.getAllCats()

    @WorkerThread
    override suspend fun insertCat(cat: Cat) = catDAO.insertCat(cat)
    override fun deleteCat(name: String) = catDAO.deleteCat(name)
}