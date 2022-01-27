package com.ms.catlife.addcat.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ms.catlife.R
import com.ms.catlife.addcat.viewmodel.AddCatViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddCatFormActivity : ComponentActivity() {

    private val addCatViewModel by viewModels<AddCatViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_cat_form)
    }
}