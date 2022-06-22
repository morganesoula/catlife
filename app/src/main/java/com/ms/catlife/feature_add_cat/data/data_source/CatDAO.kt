package com.ms.catlife.feature_add_cat.data.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ms.catlife.feature_main.domain.model.Cat
import kotlinx.coroutines.flow.Flow

@Dao
interface CatDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCat(cat: Cat)

    @Query("SELECT * FROM Cat")
    fun getAllCats(): Flow<List<Cat>>

    @Query("SELECT * FROM Cat WHERE id = :id")
    suspend fun getCatById(id: Int): Cat?

    @Query("SELECT * FROM Cat WHERE name = :name")
    suspend fun getCatByName(name: String): Cat?

    @Delete
    suspend fun deleteCat(cat: Cat)
}