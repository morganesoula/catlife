package com.ms.catlife.feature_cat_detail.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.ms.catlife.R
import com.ms.catlife.core.util.DateFormatter
import com.ms.catlife.core.util.ValidationEvent
import com.ms.catlife.feature_cat_detail.presentation.CatDetailEvent
import com.ms.catlife.theme.CatLifeTheme

@Composable
fun CatDetailBody(catId: Int, catDetailViewModel: CatDetailViewModel, navController: NavController) {
    CatLifeTheme {
        val context = LocalContext.current
        val state = catDetailViewModel.state

        if (state.customDialogState) {
            DeleteCatCustomDialogUI(context, catDetailViewModel, catId)
        }

        catDetailViewModel.onEvent(CatDetailEvent.OnCatIdReceived(catId))

        LaunchedEffect(context) {
            catDetailViewModel.validationsEvents.collect { event ->
                when (event) {
                    is ValidationEvent.Success -> navController.navigateUp()
                }
            }
        }

        BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
            Box(modifier = Modifier.fillMaxWidth().fillMaxHeight()) {
                Image(
                    modifier = Modifier.fillMaxWidth(), painter = rememberAsyncImagePainter(
                        model = ImageRequest.Builder(context).data(state.catProfilePath?.toUri()).build()
                    ), contentDescription = stringResource(R.string.uri_cat_picture), contentScale = ContentScale.Crop
                )
            }

            Box(
                modifier = Modifier.fillMaxWidth().align(Alignment.BottomStart).height(maxHeight.div(2.1f)),
                contentAlignment = Alignment.TopStart
            ) {
                Card(
                    modifier = Modifier.fillMaxSize(), shape = RoundedCornerShape(topStart = 50.dp), elevation = 5.dp
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth().padding(20.dp).verticalScroll(rememberScrollState())
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = state.catName.uppercase(), style = TextStyle(
                                    color = Color.Black, fontSize = 20.sp, fontWeight = FontWeight.Bold
                                )
                            )

                            Row {
                                IconButton(onClick = { catDetailViewModel.onEvent(CatDetailEvent.OnCatEdition(catId)) }) {
                                    Icon(
                                        imageVector = Icons.Rounded.Edit,
                                        tint = Color.Black,
                                        contentDescription = context.getString(R.string.edit)
                                    )
                                }

                                IconButton(onClick = {
                                    catDetailViewModel.onEvent(
                                        CatDetailEvent.OnDeleteCustomDialogOpen(
                                            openDialog = true, deleteData = false, catId
                                        )
                                    )
                                }) {
                                    Icon(
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = context.getString(R.string.delete),
                                        tint = Color.Black
                                    )
                                }
                            }

                        }

                        Row(modifier = Modifier.fillMaxWidth()) {
                            Icon(
                                painter = painterResource(R.drawable.ic_round_cake_24),
                                contentDescription = context.getString(R.string.birthdate)
                            )

                            Spacer(modifier = Modifier.width(10.dp))

                            Text(
                                text = DateFormatter.dateAccordingToLocale(context, state.catBirthdate),
                                style = TextStyle(color = Color.Black, fontSize = 16.sp),
                                modifier = Modifier.padding(top = 5.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(15.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(text = context.getString(R.string.neutered), fontWeight = FontWeight.Bold)
                                Text(text = if (state.neutered) context.getString(R.string.yes) else context.getString(R.string.no))
                            }

                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(text = context.getString(R.string.weight_title), fontWeight = FontWeight.Bold)
                                Text(text = state.weight.toString() + " " + context.getString(R.string.lbs))
                            }
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(text = context.getString(R.string.race_title), fontWeight = FontWeight.Bold)
                                Text(text = state.race)
                            }

                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(text = context.getString(R.string.fur), fontWeight = FontWeight.Bold)
                                Text(text = state.fur)
                            }
                        }

                        Spacer(modifier = Modifier.height(15.dp))

                        Row {
                            Text(text = "${context.getString(R.string.last_vaccine)}: ", fontWeight = FontWeight.Bold)
                            Text(text = state.lastVaccineDate?.let {
                                if (it.toInt() != 0) DateFormatter.dateAccordingToLocale(
                                    context, it
                                ) else context.getString(R.string.unknown)
                            } ?: context.getString(R.string.unknown), overflow = TextOverflow.Ellipsis)
                        }

                        Row {
                            Text(
                                text = "${context.getString(R.string.last_de_worming)}: ", fontWeight = FontWeight.Bold
                            )
                            Text(text = state.lastDewormingDate?.let {
                                if (it.toInt() != 0) DateFormatter.dateAccordingToLocale(
                                    context, it
                                ) else context.getString(R.string.unknown)
                            } ?: context.getString(R.string.unknown), overflow = TextOverflow.Ellipsis)
                        }

                        Row {
                            Text(
                                text = "${context.getString(R.string.last_flea_treatment)}: ",
                                fontWeight = FontWeight.Bold
                            )
                            Text(text = state.lastFleaDate?.let {
                                if (it.toInt() != 0) DateFormatter.dateAccordingToLocale(
                                    context, it
                                ) else context.getString(R.string.unknown)
                            } ?: context.getString(R.string.unknown), overflow = TextOverflow.Ellipsis)
                        }

                        if (!state.diseases.isNullOrBlank()) {
                            Spacer(modifier = Modifier.height(15.dp))
                            Row {
                                Text(
                                    text = "${context.getString(R.string.diseases)}: ", fontWeight = FontWeight.Bold
                                )
                                Text(text = state.diseases)
                            }
                        }
                    }
                }
            }
        }
    }
}

