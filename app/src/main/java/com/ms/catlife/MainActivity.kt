package com.ms.catlife

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.FabPosition
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ms.catlife.core.presentation.CatLifeTopBar
import com.ms.catlife.core.presentation.HomeFloatingActionButton
import com.ms.catlife.core.presentation.navigation.BottomAppBarComposable
import com.ms.catlife.core.presentation.navigation.CatLifeScreen
import com.ms.catlife.feature_add_cat.presentation.screen.AddCatViewModel
import com.ms.catlife.feature_add_cat.presentation.screen.CatFormBody
import com.ms.catlife.feature_cat_detail.presentation.screen.CatDetailBody
import com.ms.catlife.feature_cat_detail.presentation.screen.CatDetailViewModel
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
            val currentScreen = backStackEntry.value?.destination?.route

            CatLifeApp(navController, currentScreen)
        }
    }
}

@Composable
fun CatLifeApp(
    navController: NavHostController,
    currentScreen: String?,
    addCatViewModel: AddCatViewModel = hiltViewModel(),
    mainViewModel: MainViewModel = hiltViewModel(),
    catDetailViewModel: CatDetailViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    val bottomBarState = rememberSaveable { (mutableStateOf(true)) }

    CatLifeTheme {
        Scaffold(
            isFloatingActionButtonDocked = true,
            floatingActionButtonPosition = FabPosition.Center,
            floatingActionButton = {
                if (currentScreen == CatLifeScreen.HomeScreen.route) {
                    HomeFloatingActionButton(navController)
                }
            },
            bottomBar = {
                BottomAppBarComposable(
                    navController = navController,
                    bottomBarState = bottomBarState
                )
            }
        ) { contentPadding ->
            NavHost(
                navController = navController,
                startDestination = CatLifeScreen.HomeScreen.route
            ) {
                composable(CatLifeScreen.HomeScreen.route) {
                    bottomBarState.value = true

                    Scaffold(
                        topBar = { CatLifeTopBar(stringResource(R.string.app_name)) },
                        content = { HomeCatBody(contentPadding, mainViewModel, scaffoldState, scope, navController) }
                    )
                }

                composable(CatLifeScreen.AddCatFormScreen.route) {
                    bottomBarState.value = true

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

                composable(
                    route = CatLifeScreen.CatDetail.route + "?catId={catId}", arguments = listOf(
                        navArgument(name = "catId") {
                            type = NavType.IntType
                            defaultValue = -1
                        })
                ) {
                    bottomBarState.value = false
                    val id = it.arguments?.getInt("catId") ?: -1

                    Scaffold(
                        content = {
                            CatDetailBody(
                                catId = id,
                                catDetailViewModel = catDetailViewModel,
                                content = contentPadding
                            )
                        }
                    )

                }
            }
        }
    }
}



