package com.ms.catlife.di

import com.ms.catlife.feature_add_cat.domain.use_cases.cat_characteristics.ValidateCatBirthdate
import com.ms.catlife.feature_add_cat.domain.use_cases.cat_characteristics.ValidateCatCoat
import com.ms.catlife.feature_add_cat.domain.use_cases.cat_characteristics.ValidateCatDewormingDate
import com.ms.catlife.feature_add_cat.domain.use_cases.cat_characteristics.ValidateCatFleaDate
import com.ms.catlife.feature_add_cat.domain.use_cases.cat_characteristics.ValidateCatName
import com.ms.catlife.feature_add_cat.domain.use_cases.cat_characteristics.ValidateCatVaccineDate
import com.ms.catlife.feature_add_cat.domain.use_cases.cat_characteristics.ValidateCatWeight
import com.ms.catlife.feature_add_cat.domain.use_cases.cat_characteristics.ValidationUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ValidationUseCaseModule {
    @Provides
    fun provideValidationUseCases() = ValidationUseCases(
        ValidateCatName(),
        ValidateCatWeight(),
        ValidateCatCoat(),
        ValidateCatBirthdate(),
        ValidateCatVaccineDate(),
        ValidateCatFleaDate(),
        ValidateCatDewormingDate()
    )
}