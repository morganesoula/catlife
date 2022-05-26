package com.ms.catlife.domain.use_case.add_cat_form

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