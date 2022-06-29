package com.ms.catlife.feature_add_cat.domain.use_cases.cat_characteristics

import com.ms.catlife.R
import com.ms.catlife.core.util.UiText
import com.ms.catlife.util.DateFormatter

class ValidateCatDewormingDate {

    private val limitDewormingDateInMs: Long = 1009843200000

    fun execute(dewormingDate: Long): ValidationResult {
        if (dewormingDate != 0L) {
            if (dewormingDate > DateFormatter.getDefaultDateInMillis()) {
                return ValidationResult(false, UiText.StringResource(resId = R.string.wrong_input_format))
            }

            if (dewormingDate < (DateFormatter.getDefaultDateInMillis() - limitDewormingDateInMs)) {
                return ValidationResult(false, UiText.StringResource(resId = R.string.wrong_input_format))
            }
        }

        return ValidationResult(true)
    }
}