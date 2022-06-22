package com.ms.catlife.feature_add_cat.domain.use_cases.cat_characteristics

import com.ms.catlife.util.DateFormatter

class ValidateCatBirthdate {

    private val maxAgeInMs: Long = 1009843200000

    fun execute(catBirthdate: Long): ValidationResult {
        if (catBirthdate == 0L) {
            return ValidationResult(false, "blankError")
        }

        if (catBirthdate > DateFormatter.getDefaultDateInMillis()) {
            return ValidationResult(false, "future date impossible")
        }

        if (catBirthdate < (DateFormatter.getDefaultDateInMillis() - maxAgeInMs)) {
            return ValidationResult(false, "cat can't be that old...")
        }

        return ValidationResult(true)
    }
}