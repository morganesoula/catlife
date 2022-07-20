package com.ms.catlife.feature_main.presentation.screen

import android.widget.Space
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarResult
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.ms.catlife.R
import com.ms.catlife.core.util.TestTags
import com.ms.catlife.feature_main.presentation.cats.CatsEvent
import com.ms.catlife.feature_main.presentation.screen.components.CatItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun HomeCatBody(
    contentPadding: PaddingValues, mainViewModel: MainViewModel, scaffoldState: ScaffoldState, scope: CoroutineScope
) {
    Column(modifier = Modifier.padding(contentPadding)) {
        when (mainViewModel.state.cats.size) {
            0 -> NoCats()
            else -> ListOfCats(mainViewModel, scaffoldState, scope)
        }
        NextEvents()
    }
}

@Composable
fun ListOfCats(mainViewModel: MainViewModel, scaffoldState: ScaffoldState, scope: CoroutineScope) {
    val context = LocalContext.current

    MaterialTheme {
        Spacer(modifier = Modifier.height(20.dp))

        LazyRow(modifier = Modifier.fillMaxWidth().testTag(TestTags.CATS_LIST)) {
            items(mainViewModel.state.cats) { currentCat ->
                Box(modifier = Modifier.fillMaxWidth(0.5f).padding(16.dp)) {
                    CatItem(painter = rememberAsyncImagePainter(
                        model = ImageRequest.Builder(context).data(currentCat.profilePicturePath?.toUri() ?: "").build()
                    ), catName = currentCat.name, onDeleteClick = {
                        mainViewModel.onEvent(CatsEvent.DeleteCat(currentCat))

                        scope.launch {
                            val result = scaffoldState.snackbarHostState.showSnackbar(
                                message = "Cat deleted", actionLabel = "Undo"
                            )

                            if (result == SnackbarResult.ActionPerformed) {
                                mainViewModel.onEvent(CatsEvent.RestoreCat)
                            }
                        }
                    })
                }
            }
        }
    }
}

@Composable
fun NoCats() {
    val context = LocalContext.current

    Card(modifier = Modifier.fillMaxWidth().height(250.dp)) {
        Text(text = context.getString(R.string.no_cats_text), fontSize = 20.sp, textAlign = TextAlign.Center)
    }
}

@Composable
fun NextEvents() {
    val context = LocalContext.current

    MaterialTheme {
        Spacer(modifier = Modifier.height(16.dp))

        Card(modifier = Modifier.fillMaxWidth().padding(10.dp).height(150.dp)) {
            Text(
                text = context.getString(R.string.next_events_title),
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}