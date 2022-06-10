package com.ms.catlife.feature_add_cat.domain.use_cases.cat_characteristics

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: String? = null
)