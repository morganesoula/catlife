package com.ms.catlife.navigation

import java.lang.IllegalArgumentException

enum class CatLifeScreen {
    Home,
    AddCatForm;

    companion object {
        fun fromRoute(route: String?): CatLifeScreen =
            when (route?.substringBefore("/")) {
                Home.name -> Home
                AddCatForm.name -> AddCatForm
                null -> Home
                else -> throw IllegalArgumentException("Route not found: $route")
            }
    }
}