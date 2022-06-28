package com.ms.catlife.feature_main.presentation.screen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ms.catlife.MainActivity
import com.ms.catlife.core.presentation.navigation.CatLifeScreen
import com.ms.catlife.core.util.TestTags
import com.ms.catlife.di.CatLifeModule
import com.ms.catlife.theme.CatLifeTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.CoroutineScope
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(CatLifeModule::class)
class CatsScreenTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    private lateinit var mainViewModel: MainViewModel
    private lateinit var scaffoldState: ScaffoldState
    private lateinit var scope: CoroutineScope

    @Before
    fun setUp() {
        hiltRule.inject()
        composeRule.setContent {
            val navController = rememberNavController()

            CatLifeTheme {
                mainViewModel = hiltViewModel()
                scaffoldState = rememberScaffoldState()
                scope = rememberCoroutineScope()

                NavHost(
                    navController = navController,
                    startDestination = CatLifeScreen.HomeScreen.route
                ) {
                    composable(route = CatLifeScreen.HomeScreen.route) {
                        HomeCatBody(PaddingValues(300.dp), mainViewModel, scaffoldState, scope)
                    }
                }
            }
        }
    }

    @Test
    fun launchingHomeScreen_shouldHaveWelcomeMessage() {
        composeRule.onNodeWithTag(TestTags.WELCOME).assertExists()
    }

    @Test
    fun launchingHomeScreen_inexistantListOfCats() {
        composeRule.onNodeWithTag(TestTags.CATS_LIST).assertIsNotDisplayed()
    }
}