package com.ms.catlife.presentation.screen.util

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ms.catlife.R
import com.ms.catlife.presentation.navigation.CatLifeScreen

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

@Composable
fun ErrorComposable(error: String?) {
    Text(
        text = error ?: stringResource(R.string.should_not_be_empty),
        color = Color.Red,
        modifier = Modifier.fillMaxWidth().padding(start = 4.dp, end = 4.dp, bottom = 10.dp),
        textAlign = TextAlign.Center
    )
}