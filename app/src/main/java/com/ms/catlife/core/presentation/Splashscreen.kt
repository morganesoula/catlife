package com.ms.catlife.core.presentation

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.ms.catlife.MainActivity
import com.ms.catlife.R

class Splashscreen : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SplashscreenLayout()
        }

        Handler(Looper.getMainLooper()).postDelayed({
            val mainIntent = Intent(this, MainActivity::class.java)
            mainIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            finish()
            startActivity(mainIntent)
        }, 1000)
    }
}

@Preview
@Composable
private fun SplashscreenLayout() {
    Image(
        painter = painterResource(R.drawable.cat_life),
        contentDescription = "Cat life logo",
        modifier = Modifier.fillMaxSize().background(Color.Black)
    )
}