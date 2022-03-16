package com.ms.catlife

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.FabPosition
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ms.catlife.domain.model.Cat
import com.ms.catlife.presentation.composable.main.HomeCatBody
import com.ms.catlife.presentation.navigation.CatLifeScreen
import com.ms.catlife.presentation.screen.addCatForm.AddCatViewModel
import com.ms.catlife.presentation.screen.addCatForm.CatFormBody
import com.ms.catlife.presentation.screen.main.MainViewModel
import com.ms.catlife.presentation.screen.util.CatLifeTopBar
import com.ms.catlife.presentation.screen.util.HomeFloatingActionButton
import com.ms.catlife.theme.CatLifeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val listOfCats = mainViewModel.allCats

        setContent {
            val navController = rememberNavController()
            val backStackEntry = navController.currentBackStackEntryAsState()
            val currentScreen = CatLifeScreen.fromRoute(
                backStackEntry.value?.destination?.route
            )

            CatLifeApp(listOfCats, navController, currentScreen)
        }
    }
}

@Composable
private fun CatLifeApp(
    listOfCats: ArrayList<Cat>,
    navController: NavHostController,
    currentScreen: CatLifeScreen,
    addCatViewModel: AddCatViewModel = hiltViewModel()
) {
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
                        content = {
                            CatFormBody(
                                addCatViewModel,
                                contentPadding
                            )
                        }
                    )
                }
            }
        }
    }
}



