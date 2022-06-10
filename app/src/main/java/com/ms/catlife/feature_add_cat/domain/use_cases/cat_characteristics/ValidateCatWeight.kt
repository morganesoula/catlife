package com.ms.catlife.feature_add_cat.domain.use_cases.cat_characteristics

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