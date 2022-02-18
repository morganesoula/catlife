package com.ms.catlife.main.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.fragment.app.FragmentActivity
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ms.catlife.composable.addcat.AddCatFormBody
import com.ms.catlife.composable.main.HomeCatBody
import com.ms.catlife.main.data.model.Cat
import com.ms.catlife.main.viewmodel.MainViewModel
import com.ms.catlife.navigation.CatLifeScreen
import com.ms.catlife.theme.CatLifeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val listOfCats = mainViewModel.allCats

        setContent {
            CatLifeApp(listOfCats)
        }
    }
}

@Composable
private fun CatLifeApp(listOfCats: ArrayList<Cat>) {
    val navController = rememberNavController()

    CatLifeTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "Cat Life") },
                    backgroundColor = MaterialTheme.colors.secondaryVariant,
                    contentColor = Color.White,
                    navigationIcon = {
                        if (navController.previousBackStackEntry != null) {
                            IconButton(
                                onClick = {
                                    navController.popBackStack()
                                }) {
                                Icon(Icons.Filled.ArrowBack, "back", tint = Color.Black)
                            }
                        }
                    }
                )
            },
            isFloatingActionButtonDocked = true,
            floatingActionButtonPosition = FabPosition.Center,
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { navController.navigate(CatLifeScreen.AddCatForm.name) },
                    backgroundColor = MaterialTheme.colors.secondaryVariant
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Add new cat")
                }
            },
            bottomBar = {
                BottomAppBar(
                    backgroundColor = MaterialTheme.colors.background,
                    cutoutShape = CircleShape
                ) { }
            }
        ) {
            NavHost(
                navController = navController,
                startDestination = CatLifeScreen.Home.name
            ) {
                composable(CatLifeScreen.Home.name) {
                    HomeCatBody(listOfCats)
                }

                composable(CatLifeScreen.AddCatForm.name) {
                    AddCatFormBody()
                }
            }
        }
    }

}



