package com.ms.catlife

import android.os.Bundle
<<<<<<< HEAD
import androidx.appcompat.app.AppCompatActivity
=======
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
>>>>>>> 816f9592541867484f90d44e9d588ff1becfa883

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
<<<<<<< HEAD
        setContentView(R.layout.activity_main)

        val test = "test"
    }
}
=======
        setContent {
            Greeting()
        }
    }
}

@Preview
@Composable
private fun Greeting() {
    Text("Hello into Cat Life app")
}

>>>>>>> 816f9592541867484f90d44e9d588ff1becfa883
