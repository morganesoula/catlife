package com.ms.catlife.feature_add_cat.domain.use_cases.cat_characteristics

import com.ms.catlife.core.util.UiText

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: UiText? = null
)