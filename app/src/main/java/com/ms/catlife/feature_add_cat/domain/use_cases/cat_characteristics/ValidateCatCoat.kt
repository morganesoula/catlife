package com.ms.catlife.feature_add_cat.domain.use_cases.cat_characteristics

import com.ms.catlife.R
import com.ms.catlife.core.util.UiText

class ValidateCatCoat {

    fun execute(catCoat: String): ValidationResult {

        if (catCoat.isBlank()) {
            return ValidationResult(
                false, UiText.StringResource(resId = R.string.blank_error)
            )
        }

        val numericRegex = "-?\\d+(\\.\\d+)?".toRegex()

        if (catCoat.contains(numericRegex)) {
            return ValidationResult(
                false, UiText.StringResource(resId = R.string.no_number_allowed)
            )
        }

        return ValidationResult(true)
    }
}