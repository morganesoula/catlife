package com.ms.catlife.feature_add_cat.domain.use_cases.cat_characteristics

import com.ms.catlife.R
import com.ms.catlife.core.util.UiText
import com.ms.catlife.util.DateFormatter

class ValidateCatVaccineDate {

    private val limitVaccineDateInMs: Long = 1009843200000

    fun execute(vaccineDate: Long): ValidationResult {
        if (vaccineDate != 0L) {
            if (vaccineDate > DateFormatter.getDefaultDateInMillis()) {
                return ValidationResult(false, UiText.StringResource(resId = R.string.wrong_input_format))
            }

            if (vaccineDate < (DateFormatter.getDefaultDateInMillis() - limitVaccineDateInMs)) {
                return ValidationResult(false, UiText.StringResource(resId = R.string.wrong_input_format))
            }
        }

        return ValidationResult(true)
    }
}