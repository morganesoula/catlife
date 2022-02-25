package com.ms.catlife.main.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.FabPosition
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ms.catlife.R
import com.ms.catlife.addcat.viewmodel.AddCatViewModel
import com.ms.catlife.composable.addcat.AddCatFormBody
import com.ms.catlife.composable.main.HomeCatBody
import com.ms.catlife.composable.general.CatLifeTopBar
import com.ms.catlife.composable.general.HomeFloatingActionButton
import com.ms.catlife.main.data.model.Cat
import com.ms.catlife.main.viewmodel.MainViewModel
import com.ms.catlife.navigation.CatLifeScreen
import com.ms.catlife.theme.CatLifeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val mainViewModel by viewModels<MainViewModel>()
    private val addCatViewModel by viewModels<AddCatViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val listOfCats = mainViewModel.allCats

        setContent {
            CatLifeApp(listOfCats, addCatViewModel)
        }
    }
}

@Composable
private fun CatLifeApp(listOfCats: ArrayList<Cat>, addCatViewModel: AddCatViewModel) {
    val navController = rememberNavController()
    val backStackEntry = navController.currentBackStackEntryAsState()
    val currentScreen = CatLifeScreen.fromRoute(
        backStackEntry.value?.destination?.route
    )

    Log.i("XXX", "Current screen is: $currentScreen")

    CatLifeTheme {
        Scaffold(
            isFloatingActionButtonDocked = true,
            floatingActionButtonPosition = FabPosition.Center,
            floatingActionButton = {
                if (currentScreen == CatLifeScreen.Home) {
                    HomeFloatingActionButton(navController)
                }
            },
            bottomBar = {
                BottomAppBar(
                    backgroundColor = MaterialTheme.colors.background,
                    cutoutShape = CircleShape
                ) { }
            }
        ) { contentPadding ->
            NavHost(
                navController = navController,
                startDestination = CatLifeScreen.Home.name
            ) {
                composable(CatLifeScreen.Home.name) {
                    Scaffold(
                        topBar = { CatLifeTopBar(stringResource(R.string.app_name)) },
                        content = { HomeCatBody(listOfCats, contentPadding) }
                    )
                }

                composable(CatLifeScreen.AddCatForm.name) {
                    Scaffold(
                        topBar = { CatLifeTopBar(stringResource(R.string.add_cat_title)) },
                        content = { AddCatFormBody(contentPadding, addCatViewModel) }
                    )
                }
            }
        }
    }
}



