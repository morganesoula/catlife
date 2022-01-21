package com.ms.catlife.main.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ms.catlife.main.data.CatList

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val listOfCats = arrayListOf<CatList>()
        listOfCats.add(CatList("Kiwi", 0, 8))
        listOfCats.add(CatList("Douffy", 0, 13))
        listOfCats.add(CatList("Chichi", 0, 4))
        listOfCats.add(CatList("Kokorito", 0, 4))

        setContent {
            Column {
                Welcome()
                ListOfCats(listOfCats)
            }
        }
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
private fun ListOfCats(listOfCats: ArrayList<CatList>) {
    MaterialTheme {
        LazyColumn {
            items(listOfCats) { data ->
                CatListItem(data)
            }
        }
    }
}

@Composable
private fun CatListItem(catList: CatList) {
    Text(text = "Name: ${catList.name}", color = MaterialTheme.colors.primaryVariant)
    Text(text = "Age: ${catList.age}", color = MaterialTheme.colors.primaryVariant)
}