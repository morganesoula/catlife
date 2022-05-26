package com.ms.catlife.domain.use_case.add_cat_form

import com.ms.catlife.util.DateFormatter

class ValidateCatFleaDate {

    fun execute(fleaDate: Long): ValidationResult {
        if (fleaDate != 0L) {
            if (fleaDate > DateFormatter.getDefaultDateInMillis()) {
                return ValidationResult(false, "future date impossible")
            }
        }

        return ValidationResult(true)
    }
}