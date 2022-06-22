package com.ms.catlife.feature_add_cat.domain.use_cases.cat_characteristics

import com.ms.catlife.util.DateFormatter

class ValidateCatDewormingDate {

    private val limitDewormingDateInMs: Long = 1009843200000

    fun execute(dewormingDate: Long): ValidationResult {
        if (dewormingDate != 0L) {
            if (dewormingDate > DateFormatter.getDefaultDateInMillis()) {
                return ValidationResult(false, "future date impossible")
            }

            if (dewormingDate < (DateFormatter.getDefaultDateInMillis() - limitDewormingDateInMs)) {
                return ValidationResult(false, "deworming date is too old")
            }
        }

        return ValidationResult(true)
    }
}