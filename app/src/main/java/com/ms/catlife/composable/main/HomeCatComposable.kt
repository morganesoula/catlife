package com.ms.catlife.composable.main

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ms.catlife.main.data.model.Cat

@Composable
fun HomeCatBody(listOfCats: ArrayList<Cat>, contentPadding: PaddingValues) {
    Column(modifier = Modifier.padding(contentPadding)) {
        Welcome()
        ListOfCats(listOfCats)
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
@NonRestartableComposable
private fun CatListItem(cat: Cat) {
    Card(
        modifier = Modifier.padding(8.dp).fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        border = BorderStroke(2.dp, Color.Black),
        elevation = 4.dp
    ) {
        Column(modifier = Modifier.padding(5.dp)) {
            Text(text = "Name: ${cat.name}", color = MaterialTheme.colors.secondaryVariant)
            Text(text = "Age: ${cat.age}", color = MaterialTheme.colors.secondaryVariant)
        }
    }
}