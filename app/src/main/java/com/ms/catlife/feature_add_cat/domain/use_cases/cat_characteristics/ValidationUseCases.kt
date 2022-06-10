package com.ms.catlife.feature_add_cat.domain.use_cases.cat_characteristics

data class ValidationUseCases(
    val validateCatNameUseCase: ValidateCatName,
    val validateCatWeightUseCase: ValidateCatWeight,
    val validateCatCoatUseCase: ValidateCatCoat,
    val validateCatBirthdateUseCase: ValidateCatBirthdate,
    val validateCatVaccineDateUseCase: ValidateCatVaccineDate,
    val validateCatFleaDateUseCase: ValidateCatFleaDate,
    val validateCatDewormingDateUseCase: ValidateCatDewormingDate
)
