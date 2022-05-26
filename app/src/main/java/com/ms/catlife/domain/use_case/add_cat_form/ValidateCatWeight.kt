package com.ms.catlife.domain.use_case.add_cat_form

class ValidateCatWeight {

    fun execute(weight: String): ValidationResult {
        if (weight.isEmpty()) {
            return ValidationResult(
                false,
                "blank weight"
            )
        }

        if (weight.toFloatOrNull() == null) {
            return ValidationResult(false, "input format error")
        }
    
        if (weight.toFloat() <= 0 || weight.toFloat() > 22) {
            return ValidationResult(
                false,
                "weightError"
            )
        }

        return ValidationResult(true)
    }
}