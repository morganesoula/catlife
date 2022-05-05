package com.ms.catlife.domain.use_case.add_cat_form

class ValidateCatName {

    fun execute(catName: String): ValidationResult {
        if (catName.isBlank()) {
            return ValidationResult(
                false, "Cat name can not be blank"
            )
        }

        val numericRegex = "-?[0-9]+(\\.[0-9]+)?".toRegex()

        if (catName.matches(numericRegex)) {
            return ValidationResult(
                false, "Cat name can not include numbers"
            )
        }

        return ValidationResult(true)
    }
}