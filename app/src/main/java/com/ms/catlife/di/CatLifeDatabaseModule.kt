package com.ms.catlife.di

import android.app.Application
import androidx.room.Room
import com.ms.catlife.data.db.CatDAO
import com.ms.catlife.data.db.CatDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CatLifeDatabaseModule {

    @Provides
    @Singleton
    fun provideCatLifeDatabase(app: Application): CatDB =
        Room.databaseBuilder(
            app,
            CatDB::class.java,
            "cat_life_database"
        ).build()

    @Provides
    fun provideCatDAO(catDB: CatDB): CatDAO = catDB.catDAO()
}