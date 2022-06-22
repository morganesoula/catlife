package com.ms.catlife

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.FabPosition
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ms.catlife.core.presentation.navigation.CatLifeScreen
import com.ms.catlife.feature_add_cat.presentation.screen.AddCatViewModel
import com.ms.catlife.feature_add_cat.presentation.screen.CatFormBody
import com.ms.catlife.core.presentation.CatLifeTopBar
import com.ms.catlife.core.presentation.HomeFloatingActionButton
import com.ms.catlife.feature_main.presentation.screen.HomeCatBody
import com.ms.catlife.feature_main.presentation.screen.MainViewModel
import com.ms.catlife.theme.CatLifeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            val backStackEntry = navController.currentBackStackEntryAsState()
            val currentScreen = CatLifeScreen.fromRoute(
                backStackEntry.value?.destination?.route
            )

            CatLifeApp(navController, currentScreen)
        }
    }
}

@Composable
private fun CatLifeApp(
    navController: NavHostController,
    currentScreen: CatLifeScreen,
    addCatViewModel: AddCatViewModel = hiltViewModel(),
    mainViewModel: MainViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

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
                        content = { HomeCatBody(contentPadding, mainViewModel, scaffoldState, scope) }
                    )
                }

                composable(CatLifeScreen.AddCatForm.name) {
                    Scaffold(
                        topBar = { CatLifeTopBar(stringResource(R.string.add_cat_title)) },
                        content = {
                            CatFormBody(
                                addCatViewModel = addCatViewModel,
                                content = contentPadding,
                                navController = navController
                            )
                        }
                    )
                }
            }
        }
    }
}



