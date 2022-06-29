package com.ms.catlife.feature_add_cat.domain.use_cases.cat_characteristics

import com.ms.catlife.R
import com.ms.catlife.core.util.UiText
import com.ms.catlife.util.DateFormatter

class ValidateCatBirthdate {

    private val maxAgeInMs: Long = 1009843200000

    fun execute(catBirthdate: Long): ValidationResult {
        if (catBirthdate == 0L) {
            return ValidationResult(false, UiText.StringResource(resId = R.string.blank_error))
        }

        if (catBirthdate > DateFormatter.getDefaultDateInMillis()) {
            return ValidationResult(false, UiText.StringResource(resId = R.string.wrong_input_format))
        }

        if (catBirthdate < (DateFormatter.getDefaultDateInMillis() - maxAgeInMs)) {
            return ValidationResult(false, UiText.StringResource(resId = R.string.wrong_input_format))
        }

        return ValidationResult(true)
    }
}