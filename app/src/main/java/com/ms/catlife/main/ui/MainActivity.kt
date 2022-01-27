package com.ms.catlife.main.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.ms.catlife.addcat.ui.AddCatFormActivity
import com.ms.catlife.main.data.model.Cat
import com.ms.catlife.main.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val listOfCats = mainViewModel.allCats

        setContent {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text(text = "Cat Life") },
                        backgroundColor = MaterialTheme.colors.secondaryVariant,
                        contentColor = Color.White,
                        navigationIcon = null
                    )
                },
                content = {
                    Column {
                        Welcome()
                        ListOfCats(listOfCats)
                    }
                },
                isFloatingActionButtonDocked = true,
                floatingActionButtonPosition = FabPosition.Center,
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = { startAddCatActivity() },
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

            )
        }
    }

    private fun startAddCatActivity() {
        val addCatIntent = Intent(this, AddCatFormActivity::class.java)
        startActivity(addCatIntent)
    }
}

@Preview
@Composable
private fun Welcome() {
    MaterialTheme {
        Box(modifier = Modifier.fillMaxWidth()) {
            Text(modifier = Modifier.align(Alignment.TopCenter), text = "Bienvenue")
        }
    }
}

@Composable
private fun ListOfCats(listOfCats: ArrayList<Cat>?) {
    MaterialTheme {
        LazyColumn {
            listOfCats?.let {
                items(it) { data ->
                    CatListItem(data)
                }
            }
        }
    }
}

@Composable
private fun CatListItem(cat: Cat) {
    Text(text = "Name: ${cat.name}", color = MaterialTheme.colors.primaryVariant)
    Text(text = "Age: ${cat.age}", color = MaterialTheme.colors.primaryVariant)
}