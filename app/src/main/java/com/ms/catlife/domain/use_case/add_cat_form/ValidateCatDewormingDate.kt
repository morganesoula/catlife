package com.ms.catlife.domain.use_case.add_cat_form

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