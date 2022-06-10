package com.ms.catlife.feature_add_cat.domain.use_cases.cat_characteristics

import com.ms.catlife.util.DateFormatter

class ValidateCatDewormingDate {

    fun execute(dewormingDate: Long): ValidationResult {
        if (dewormingDate != 0L) {
            if (dewormingDate > DateFormatter.getDefaultDateInMillis()) {
                return ValidationResult(false, "future date impossible")
            }
        }

        return ValidationResult(true)
    }
}