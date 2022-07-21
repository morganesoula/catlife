package com.ms.catlife.feature_main.presentation.screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ms.catlife.core.presentation.navigation.CatLifeScreen

@Composable
fun CatItem(
    painter: Painter,
    catName: String,
    catGender: Boolean,
    catId: Int,
    modifier: Modifier = Modifier,
    navController: NavController
) {
    Card(
        modifier = modifier.fillMaxWidth().clickable {
            navController.navigate(CatLifeScreen.CatDetail.route + "?catId=$catId")
        }, shape = RoundedCornerShape(15.dp), elevation = 5.dp
    ) {
        Box(modifier = Modifier.width(250.dp).height(250.dp)) {
            Image(
                painter = painter,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth()
            )

            // Gradient
            Box(
                modifier = Modifier.fillMaxSize().background(
                        brush = Brush.verticalGradient(
                            colors = if (catGender) listOf(
                                Color.Transparent, Color(red = 102, green = 178, blue = 255)
                            ) else listOf(Color.Transparent, Color(red = 255, green = 204, blue = 229)), startY = 300f
                        )
                    )
            )

            Text(
                catName.uppercase(),
                style = TextStyle(color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(12.dp)
            )
        }
    }
}