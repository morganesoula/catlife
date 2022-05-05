package com.ms.catlife.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ms.catlife.domain.model.Cat

@Database(
    entities = [Cat::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class CatDB : RoomDatabase() {
    abstract fun catDAO(): CatDAO
}