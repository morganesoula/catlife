package com.ms.catlife.domain.use_case.add_cat_form

class ValidateCatWeight {

    fun execute(weight: Double): ValidationResult {
        if (weight <= 0 || weight > 22) {
            return ValidationResult(
                false,
                "Weight should be included between 0.1 and 22"
            )
        }

        return ValidationResult(true)
    }
}