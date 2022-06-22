package com.ms.catlife.feature_add_cat.domain.use_cases.cat_characteristics

import com.ms.catlife.util.DateFormatter

class ValidateCatVaccineDate {

    private val limitVaccineDateInMs: Long = 1009843200000

    fun execute(vaccineDate: Long): ValidationResult {
        if (vaccineDate != 0L) {
            if (vaccineDate > DateFormatter.getDefaultDateInMillis()) {
                return ValidationResult(false, "future date impossible")
            }

            if (vaccineDate < (DateFormatter.getDefaultDateInMillis() - limitVaccineDateInMs)) {
                return ValidationResult(false, "vaccine date is too old")
            }
        }

        return ValidationResult(true)
    }
}