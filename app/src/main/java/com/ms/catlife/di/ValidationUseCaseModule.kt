package com.ms.catlife.di

import com.ms.catlife.domain.use_case.add_cat_form.ValidateCatBirthdate
import com.ms.catlife.domain.use_case.add_cat_form.ValidateCatCoat
import com.ms.catlife.domain.use_case.add_cat_form.ValidateCatDewormingDate
import com.ms.catlife.domain.use_case.add_cat_form.ValidateCatDiseases
import com.ms.catlife.domain.use_case.add_cat_form.ValidateCatFleaDate
import com.ms.catlife.domain.use_case.add_cat_form.ValidateCatName
import com.ms.catlife.domain.use_case.add_cat_form.ValidateCatVaccineDate
import com.ms.catlife.domain.use_case.add_cat_form.ValidateCatWeight
import com.ms.catlife.domain.use_case.add_cat_form.ValidationUseCases
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
        ValidateCatDiseases(),
        ValidateCatBirthdate(),
        ValidateCatVaccineDate(),
        ValidateCatFleaDate(),
        ValidateCatDewormingDate()
    )
}