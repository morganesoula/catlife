package com.ms.catlife.presentation.screen.util

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
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

@Composable
fun ImagePickedView(
    modifier: Modifier = Modifier,
    lastSelectedImage: Uri?,
    onSelection: (Uri?) -> Unit
) {
    val galleryLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) {
        onSelection(it)
    }

    Image(
        modifier = modifier
            .size(100.dp)
            .clip(CircleShape)
            .background(Color.LightGray)
            .clickable {
                galleryLauncher.launch("image/*")
            },
        painter = rememberImagePainter(lastSelectedImage),
        contentDescription = stringResource(R.string.profile_picture),
        contentScale = ContentScale.Crop
    )
}