package com.ms.catlife.feature_add_cat.domain.use_cases.cat_characteristics

import com.ms.catlife.R
import com.ms.catlife.core.util.UiText
import com.ms.catlife.util.DateFormatter

class ValidateCatFleaDate {

    private val limitFleaDateInMs: Long = 1009843200000

    fun execute(fleaDate: Long): ValidationResult {
        if (fleaDate != 0L) {
            if (fleaDate > DateFormatter.getDefaultDateInMillis()) {
                return ValidationResult(false, UiText.StringResource(resId = R.string.wrong_input_format))
            }

            if (fleaDate < (DateFormatter.getDefaultDateInMillis() - limitFleaDateInMs)) {
                return ValidationResult(false, UiText.StringResource(resId = R.string.wrong_input_format))
            }
        }

        return ValidationResult(true)
    }
}