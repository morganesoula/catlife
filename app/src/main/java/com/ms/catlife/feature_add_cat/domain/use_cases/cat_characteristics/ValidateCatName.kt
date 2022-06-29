package com.ms.catlife.feature_add_cat.domain.use_cases.cat_characteristics

import com.ms.catlife.R
import com.ms.catlife.core.util.UiText

class ValidateCatName {

    fun execute(catName: String): ValidationResult {
        if (catName.isBlank()) {
            return ValidationResult(
                false, UiText.StringResource(resId = R.string.blank_error)
            )
        }

        val numericRegex = "-?\\d+(\\.\\d+)?".toRegex()

        if (catName.contains(numericRegex)) {
            return ValidationResult(
                false, UiText.StringResource(resId = R.string.no_number_allowed)
            )
        }

        return ValidationResult(true)
    }
}