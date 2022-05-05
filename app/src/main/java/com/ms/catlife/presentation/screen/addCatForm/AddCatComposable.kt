package com.ms.catlife.presentation.screen.addCatForm

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.ms.catlife.R
import com.ms.catlife.presentation.add_cat_form.AddCatFormEvent
import com.ms.catlife.theme.CatLifeTheme

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("RememberReturnType")
@Composable
fun CatFormBody(addCatViewModel: AddCatViewModel) {
    CatLifeTheme {
        val state = addCatViewModel.state
        val context = LocalContext.current

        val catRaces: List<String> = getAllRaces(context)

        var expanded by remember { mutableStateOf(false) }
        var selectedRaceText by remember { mutableStateOf(catRaces[0]) }

        LaunchedEffect(context) {
            addCatViewModel.validationEvents.collect { event ->
                when (event) {
                    is AddCatViewModel.ValidationEvent.Success -> Toast.makeText(
                        context,
                        "Valide form",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        Column(modifier = Modifier.fillMaxSize().padding(32.dp), verticalArrangement = Arrangement.Center) {
            TextField(
                value = state.catName,
                onValueChange = {
                    addCatViewModel.onEvent(AddCatFormEvent.CatNameChanged(it))
                },
                isError = state.catNameError != null,
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text("Cat name")
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )

            if (state.catNameError != null) {
                Text(
                    text = state.catNameError,
                    color = MaterialTheme.colors.error,
                    modifier = Modifier.align(Alignment.End)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }
            ) {
                TextField(
                    readOnly = true,
                    value = selectedRaceText.toString(),
                    onValueChange = { },
                    label = { Text("Race") }
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    catRaces.forEach { selectedRace ->
                        DropdownMenuItem(
                            onClick = {
                                selectedRaceText = selectedRace
                                expanded = false
                            }
                        ) {
                            Text(text = selectedRace)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = state.weight.toString(),
                onValueChange = {
                    addCatViewModel.onEvent(AddCatFormEvent.CatWeightChanged(it.toDouble()))
                },
                isError = state.weightError != null,
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text("Weight")
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            if (state.weightError != null) {
                Text(
                    text = state.weightError,
                    color = MaterialTheme.colors.error,
                    modifier = Modifier.align(Alignment.End)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { addCatViewModel.onEvent(AddCatFormEvent.Submit) },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text(text = "Add this cat!")
            }
        }
    }
}

private fun getAllRaces(context: Context): List<String> =
    listOf(
        context.getString(R.string.abyssinian),
        context.getString(R.string.american_bobtail),
        context.getString(R.string.american_curl),
        context.getString(R.string.american_shorthair),
        context.getString(R.string.american_wirehair),
        context.getString(R.string.arabian_mau),
        context.getString(R.string.australian_mist),
        context.getString(R.string.balinese),
        context.getString(R.string.bambino),
        context.getString(R.string.bengal),
        context.getString(R.string.birman),
        context.getString(R.string.bombay),
        context.getString(R.string.british_longhair),
        context.getString(R.string.british_shorthair),
        context.getString(R.string.burmese),
        context.getString(R.string.california_spangled),
        context.getString(R.string.chantilly_tiffany),
        context.getString(R.string.chartreux),
        context.getString(R.string.chausie),
        context.getString(R.string.cheetoh),
        context.getString(R.string.colorpoint_shorthair),
        context.getString(R.string.cornish_rex),
        context.getString(R.string.cymric),
        context.getString(R.string.cyprus),
        context.getString(R.string.devon_rex),
        context.getString(R.string.donskoy),
        context.getString(R.string.dragon_li),
        context.getString(R.string.egyptian_mau),
        context.getString(R.string.european),
        context.getString(R.string.european_burmese),
        context.getString(R.string.exotic_shorthair),
        context.getString(R.string.havana_brown),
        context.getString(R.string.himalayan),
        context.getString(R.string.japanese_bobtail),
        context.getString(R.string.javanese),
        context.getString(R.string.khao_manee),
        context.getString(R.string.korat),
        context.getString(R.string.kurilian),
        context.getString(R.string.laperm),
        context.getString(R.string.maine_coon),
        context.getString(R.string.malayan),
        context.getString(R.string.manx),
        context.getString(R.string.munchkin),
        context.getString(R.string.nebelung),
        context.getString(R.string.norwegian_forest_cat),
        context.getString(R.string.ocicat),
        context.getString(R.string.oriental),
        context.getString(R.string.persian),
        context.getString(R.string.pixie_bob),
        context.getString(R.string.ragamuffin),
        context.getString(R.string.ragdoll),
        context.getString(R.string.russian_blue),
        context.getString(R.string.savannah),
        context.getString(R.string.scottish_fold),
        context.getString(R.string.selkirk_rex),
        context.getString(R.string.siamese),
        context.getString(R.string.siberian),
        context.getString(R.string.singapura),
        context.getString(R.string.snowshoe),
        context.getString(R.string.somali),
        context.getString(R.string.sphynx),
        context.getString(R.string.tonkinese),
        context.getString(R.string.toyger),
        context.getString(R.string.turkish_angora),
        context.getString(R.string.turkish_van),
        context.getString(R.string.york_chocolate)
    )





