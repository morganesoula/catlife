package com.ms.catlife.feature_cat_detail.presentation.screen

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.ms.catlife.R
import com.ms.catlife.feature_cat_detail.presentation.CatDetailEvent

@Composable
fun DeleteCatCustomDialogUI(
    context: Context,
    catDetailViewModel: CatDetailViewModel,
    catId: Int
) {
    Dialog(onDismissRequest = {
        catDetailViewModel.onEvent(
            CatDetailEvent.OnDeleteCustomDialogOpen(
                openDialog = false,
                deleteData = false,
                catId
            )
        )
    }) {
        Card(
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.padding(15.dp, 0.dp, 15.dp, 15.dp),
            elevation = 8.dp
        ) {
            Column(modifier = Modifier.background(Color.White)) {
                Image(
                    painter = painterResource(R.drawable.ic_action_delete),
                    contentDescription = context.getString(R.string.delete),
                    contentScale = ContentScale.Fit,
                    colorFilter = ColorFilter.tint(color = MaterialTheme.colors.primary),
                    modifier = Modifier.padding(top = 35.dp).height(70.dp).fillMaxWidth()
                )

                Text(
                    text = context.getString(R.string.delete_confirmation),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 5.dp).fillMaxWidth(),
                    style = MaterialTheme.typography.body1
                )

                Row(
                    modifier = Modifier.fillMaxWidth().padding(top = 10.dp)
                        .background(Color.White),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    TextButton(
                        onClick = {
                            catDetailViewModel.onEvent(
                                CatDetailEvent.OnDeleteCustomDialogOpen(
                                    openDialog = false,
                                    deleteData = false,
                                    catId
                                )
                            )
                        }
                    ) {
                        Text(
                            text = context.getString(R.string.no),
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            modifier = Modifier.padding(top = 5.dp, bottom = 5.dp)
                        )
                    }

                    TextButton(
                        onClick = {
                            catDetailViewModel.onEvent(
                                CatDetailEvent.OnDeleteCustomDialogOpen(
                                    openDialog = false,
                                    deleteData = true,
                                    catId
                                )
                            )
                        }
                    ) {
                        Text(
                            text = context.getString(R.string.yes),
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.Red,
                            modifier = Modifier.padding(top = 5.dp, bottom = 5.dp)
                        )
                    }
                }
            }
        }
    }
}