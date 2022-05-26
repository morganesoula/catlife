package com.ms.catlife.domain.use_case.add_cat_form

import com.ms.catlife.util.DateFormatter

class ValidateCatBirthdate {

    fun execute(catBirthdate: Long): ValidationResult {
        if (catBirthdate == 0L) {
            return ValidationResult(false, "blankError")
        }

        if (catBirthdate > DateFormatter.getDefaultDateInMillis()) {
            return ValidationResult(false, "future date impossible")
        }

        return ValidationResult(true)
    }
}