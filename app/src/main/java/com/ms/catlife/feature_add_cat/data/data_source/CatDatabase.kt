package com.ms.catlife.feature_add_cat.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ms.catlife.feature_main.domain.model.Cat

@Database(
    entities = [Cat::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class CatDatabase : RoomDatabase() {
    abstract val catDAO: CatDAO

    companion object {
        const val DATABASE_NAME = "cat_life_database"
    }
}