package com.ms.catlife.core.presentation.navigation

sealed class CatLifeScreen(val route: String) {
    object HomeScreen : CatLifeScreen("home_screen")
    object AddCatFormScreen : CatLifeScreen("add_cat_form_screen")
    object CatDetail: CatLifeScreen("cat_detail_screen")
}