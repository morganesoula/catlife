package com.ms.catlife.main.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ms.catlife.main.data.model.Cat
import kotlinx.coroutines.flow.Flow

@Dao
interface ICatDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertCat(cat: Cat)

    @Query("SELECT * FROM Cat ORDER BY name")
    fun getAllCats(): Flow<List<Cat>>?

    @Query("DELETE FROM Cat WHERE name = :name")
    fun deleteCat(name: String)
}