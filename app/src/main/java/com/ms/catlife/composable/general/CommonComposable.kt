package com.ms.catlife.composable.general

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.ms.catlife.navigation.CatLifeScreen

@Composable
fun CatLifeTopBar(
    title: String
) {
    TopAppBar(
        title = { Text(text = title) },
        backgroundColor = MaterialTheme.colors.secondaryVariant,
        contentColor = Color.White
    )
}

@Composable
fun HomeFloatingActionButton(navController: NavController) {
    FloatingActionButton(
        onClick = { navController.navigate(CatLifeScreen.AddCatForm.name) },
        backgroundColor = MaterialTheme.colors.secondaryVariant
    ) {
        Icon(Icons.Default.Add, contentDescription = "Add new cat")
    }
}