package com.ms.catlife.feature_add_cat.domain.use_cases.cat_characteristics

import com.ms.catlife.R
import com.ms.catlife.core.util.UiText

class ValidateCatWeight {

    fun execute(weight: String): ValidationResult {
        if (weight.isEmpty()) {
            return ValidationResult(
                false,
                UiText.StringResource(resId = R.string.blank_error)
            )
        }

        if (weight.toFloatOrNull() == null) {
            return ValidationResult(false, UiText.StringResource(resId = R.string.wrong_input_format))
        }

        if (weight.toFloat() <= 0 || weight.toFloat() > 22) {
            return ValidationResult(
                false,
                UiText.StringResource(resId = R.string.weight_error)
            )
        }

        return ValidationResult(true)
    }
}