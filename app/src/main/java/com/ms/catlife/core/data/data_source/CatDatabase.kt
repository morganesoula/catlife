package com.ms.catlife.core.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ms.catlife.core.data.entities.Cat

@Database(
    entities = [Cat::class],
    version = 7,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class CatDatabase : RoomDatabase() {
    abstract val catDAO: CatDAO

    companion object {
        const val DATABASE_NAME = "cat_life_database"
    }
}