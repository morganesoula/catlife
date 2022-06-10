package com.ms.catlife.feature_add_cat.domain.use_cases.cat_characteristics

import com.ms.catlife.util.DateFormatter

class ValidateCatVaccineDate {

    fun execute(vaccineDate: Long): ValidationResult {
        if (vaccineDate != 0L) {
            if (vaccineDate > DateFormatter.getDefaultDateInMillis()) {
                return ValidationResult(false, "future date impossible")
            }
        }

        return ValidationResult(true)
    }
}