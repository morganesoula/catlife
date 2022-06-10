package com.ms.catlife.feature_add_cat.domain.use_cases.cat_characteristics

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