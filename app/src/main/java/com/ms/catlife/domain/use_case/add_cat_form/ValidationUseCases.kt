package com.ms.catlife.domain.use_case.add_cat_form

data class ValidationUseCases(
    val validateCatNameUseCase: ValidateCatName,
    val validateCatWeightUseCase: ValidateCatWeight
)
