package com.ms.catlife.feature_main.presentation

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.FabPosition
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToIndex
import androidx.compose.ui.test.performTextInput
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import com.ms.catlife.MainActivity
import com.ms.catlife.R
import com.ms.catlife.core.presentation.CatLifeTopBar
import com.ms.catlife.core.presentation.HomeFloatingActionButton
import com.ms.catlife.core.presentation.navigation.CatLifeScreen
import com.ms.catlife.core.util.TestTags
import com.ms.catlife.di.CatLifeModule
import com.ms.catlife.feature_add_cat.presentation.screen.AddCatViewModel
import com.ms.catlife.feature_add_cat.presentation.screen.CatFormBody
import com.ms.catlife.feature_main.presentation.screen.HomeCatBody
import com.ms.catlife.feature_main.presentation.screen.MainViewModel
import com.ms.catlife.theme.CatLifeTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.CoroutineScope
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runners.MethodSorters

@HiltAndroidTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@UninstallModules(CatLifeModule::class)
class CatsEndToEndTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    private lateinit var mainViewModel: MainViewModel
    private lateinit var addCatViewModel: AddCatViewModel
    private lateinit var scaffoldState: ScaffoldState
    private lateinit var scope: CoroutineScope

    private lateinit var context: android.content.Context

    @Before
    fun setUp() {
        hiltRule.inject()
        context = ApplicationProvider.getApplicationContext()

        composeRule.setContent {
            val navController = rememberNavController()
            val backStackEntry = navController.currentBackStackEntryAsState()
            val currentScreen = backStackEntry.value?.destination?.route

            CatLifeTheme {
                mainViewModel = hiltViewModel()
                addCatViewModel = hiltViewModel()
                scaffoldState = rememberScaffoldState()
                scope = rememberCoroutineScope()

                Scaffold(
                    isFloatingActionButtonDocked = true,
                    floatingActionButtonPosition = FabPosition.Center,
                    floatingActionButton = {
                        if (currentScreen == CatLifeScreen.HomeScreen.route) {
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
                        startDestination = CatLifeScreen.HomeScreen.route
                    ) {
                        composable(CatLifeScreen.HomeScreen.route) {
                            Scaffold(
                                topBar = { CatLifeTopBar(stringResource(R.string.app_name)) },
                                content = { HomeCatBody(contentPadding, mainViewModel, scaffoldState, scope) }
                            )
                        }

                        composable(CatLifeScreen.AddCatFormScreen.route) {
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
    }

    @Test
    fun a_createNewCat_saveCat() {
        composeRule
            .onNodeWithContentDescription("Add new cat")
            .performClick()

        composeRule
            .onNodeWithTag(TestTags.CAT_NAME_TEXT_FIELD)
            .performTextInput("Chichi")

        Espresso.closeSoftKeyboard()

        composeRule
            .onNodeWithTag(TestTags.CAT_WEIGHT_FIELD)
            .performTextInput("3.7")

        Espresso.closeSoftKeyboard()

        composeRule
            .onNodeWithTag(TestTags.CAT_COAT_FIELD)
            .performTextInput("Blanc")

        Espresso.closeSoftKeyboard()

        composeRule
            .onNodeWithTag(TestTags.SUBMIT_CAT_BUTTON)
            .performClick()
    }
}