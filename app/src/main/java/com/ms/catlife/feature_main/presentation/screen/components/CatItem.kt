package com.ms.catlife.feature_main.presentation.screen.components

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.ms.catlife.R
import com.ms.catlife.feature_main.domain.model.Cat
import com.ms.catlife.feature_main.presentation.util.CatGenderFormat

@Composable
fun CatItem(
    cat: Cat, modifier: Modifier = Modifier, cornerRadius: Dp = 10.dp, onDeleteClick: () -> Unit, context: Context
) {
    allowUriPermission(cat.profilePicture, context)

    Box(
        modifier = modifier.padding(start = 10.dp)
    ) {
        Card(
            modifier = modifier.align(Alignment.TopCenter),
            shape = RoundedCornerShape(cornerRadius),
            backgroundColor = MaterialTheme.colors.surface
        ) {

            Column {
                Image(
                    modifier = Modifier.size(200.dp).clip(CircleShape),
                    contentScale = ContentScale.Crop,
                    painter = rememberAsyncImagePainter(
                        model = Uri.parse(cat.profilePicture)
                    ),
                    contentDescription = null
                )

                Spacer(modifier = modifier.height(5.dp))

                Row {
                    Text(
                        cat.name,
                        style = MaterialTheme.typography.h6,
                        color = Color.Black,
                        modifier = modifier.padding(start = 10.dp, bottom = 10.dp)
                    )
                    Icon(
                        modifier = Modifier.width(10.dp).height(10.dp),
                        painter = if (CatGenderFormat.isMaleFromString(
                                gender = cat.gender, context = context
                            )
                        ) {
                            painterResource(R.drawable.ic_action_male_light)
                        } else {
                            painterResource(R.drawable.ic_action_female_light)
                        },
                        contentDescription = null
                    )
                }
            }
            Spacer(modifier.fillMaxWidth().height(10.dp))
            IconButton(
                onClick = onDeleteClick,
                modifier = Modifier.align(Alignment.BottomEnd)
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = null
                )
            }
        }
    }
}

private fun allowUriPermission(catPictureURI: String?, context: Context) {
    catPictureURI?.let {
        context.contentResolver.takePersistableUriPermission(
            Uri.parse(it),
            Intent.FLAG_GRANT_READ_URI_PERMISSION
        )
    }
}