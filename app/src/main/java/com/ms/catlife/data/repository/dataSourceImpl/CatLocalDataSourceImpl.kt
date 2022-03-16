package com.ms.catlife.data.repository.dataSourceImpl

import com.ms.catlife.data.db.CatDAO
import com.ms.catlife.data.repository.dataSource.CatLocalDataSource
import com.ms.catlife.domain.model.Cat
import kotlinx.coroutines.flow.Flow

class CatLocalDataSourceImpl(private val catDAO: CatDAO) : CatLocalDataSource {
    override fun getAllCats(): Flow<Cat>? = catDAO.getAllCats()
    override fun insertCat(cat: Cat) = catDAO.insertCat(cat)
    override fun deleteCat(name: String) = catDAO.deleteCat(name)
}