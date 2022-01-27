package com.ms.catlife.di

import android.content.Context
import androidx.room.Room
import com.ms.catlife.main.data.dao.ICatDAO
import com.ms.catlife.util.database.CatLifeDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class CatLifeDatabaseModule {

    @Provides
    fun provideCatDAO(catLifeDatabase: CatLifeDatabase): ICatDAO = catLifeDatabase.catDAO()

    @Provides
    @Singleton
    fun provideCatLifeDatabase(@ApplicationContext context: Context): CatLifeDatabase =
        Room.databaseBuilder(
            context,
            CatLifeDatabase::class.java,
            "cat_life_database"
        ).build()
}