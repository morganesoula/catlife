package com.ms.catlife.feature_cat_detail.presentation.screen

import android.os.Build
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.ms.catlife.R
import com.ms.catlife.core.util.DateFormatter
import com.ms.catlife.feature_cat_detail.presentation.CatDetailEvent
import com.ms.catlife.theme.CatLifeTheme

@Composable
fun CatDetailBody(catId: Int, catDetailViewModel: CatDetailViewModel, content: PaddingValues) {
    CatLifeTheme {
        val state = catDetailViewModel.state
        val context = LocalContext.current

        catDetailViewModel.onEvent(CatDetailEvent.OnCatIdReceived(catId))

        BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
            Box(modifier = Modifier.fillMaxWidth().fillMaxHeight()) {
                Image(
                    modifier = Modifier.fillMaxWidth(), painter = rememberAsyncImagePainter(
                        model = ImageRequest.Builder(context).data(state.catProfilePath?.toUri()).build()
                    ), contentDescription = stringResource(R.string.uri_cat_picture), contentScale = ContentScale.Crop
                )
            }

            Box(
                modifier = Modifier.fillMaxWidth().align(Alignment.BottomStart).height(maxHeight.div(2.2f)),
                contentAlignment = Alignment.TopStart
            ) {
                Card(
                    modifier = Modifier.fillMaxSize(),
                    shape = RoundedCornerShape(topStart = 50.dp),
                    elevation = 5.dp
                ) {
                    Column(modifier = Modifier.fillMaxWidth().padding(30.dp)) {
                        Text(text = state.catName, style = TextStyle(color = Color.Black, fontSize = 20.sp))

                        Row(modifier = Modifier.fillMaxWidth()) {
                            Icon(
                                painter = painterResource(R.drawable.ic_round_cake_24),
                                contentDescription = context.getString(R.string.birthdate)
                            )

                            Spacer(modifier = Modifier.width(10.dp))

                            Text(
                                text = DateFormatter.longToDate(
                                    state.catBirthdate, if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                        context.resources.configuration.locales[0]
                                    } else {
                                        @Suppress("DEPRECATION") context.resources.configuration.locale
                                    }

                                ),
                                style = TextStyle(color = Color.Black, fontSize = 16.sp),
                                modifier = Modifier.padding(top = 5.dp)
                            )
                        }
                    }
                }
            }

        }

    }
}