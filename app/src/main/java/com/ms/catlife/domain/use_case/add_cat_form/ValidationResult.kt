package com.ms.catlife.domain.use_case.add_cat_form

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: String? = null
)