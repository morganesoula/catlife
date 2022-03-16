package com.ms.catlife.util.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ms.catlife.data.db.CatDAO
import com.ms.catlife.domain.model.Cat

@Database(
    entities = [Cat::class],
    version = 1,
    exportSchema = false
)
abstract class CatLifeDatabase : RoomDatabase() {

    abstract fun catDAO(): CatDAO

    companion object {
        @Volatile
        private var INSTANCE: CatLifeDatabase? = null

        fun getDatabase(context: Context): CatLifeDatabase =
            INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    CatLifeDatabase::class.java,
                    "cat_life_database"
                ).build()

                INSTANCE = instance
                instance
            }
    }
}