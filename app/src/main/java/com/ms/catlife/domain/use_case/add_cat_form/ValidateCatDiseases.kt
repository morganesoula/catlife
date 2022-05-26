package com.ms.catlife.domain.use_case.add_cat_form

class ValidateCatDiseases {

    fun execute(diseases: String): ValidationResult {
        return ValidationResult(true)
    }
}