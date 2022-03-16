package com.ms.catlife.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ms.catlife.domain.model.Cat
import kotlinx.coroutines.flow.Flow

@Dao
interface CatDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertCat(cat: Cat)

    @Query("SELECT * FROM Cat ORDER BY name")
    fun getAllCats(): Flow<Cat>?

    @Query("DELETE FROM Cat WHERE name = :name")
    fun deleteCat(name: String)
}