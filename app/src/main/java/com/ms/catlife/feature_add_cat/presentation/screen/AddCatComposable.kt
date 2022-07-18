@file:Suppress("unused")

package com.ms.catlife.feature_add_cat.presentation.screen

import android.content.Context
import android.content.ContextWrapper
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.google.android.material.datepicker.MaterialDatePicker
import com.ms.catlife.R
import com.ms.catlife.core.util.TestTags
import com.ms.catlife.core.util.getFileName
import com.ms.catlife.feature_add_cat.presentation.AddCatDateEvent
import com.ms.catlife.feature_add_cat.presentation.AddCatFormEvent
import com.ms.catlife.theme.CatLifeTheme
import com.ms.catlife.util.DateFormatter

@OptIn(ExperimentalMaterialApi::class)
@Suppress("FunctionName")
@Composable
fun CatFormBody(navController: NavController, addCatViewModel: AddCatViewModel, content: PaddingValues) {

    CatLifeTheme {
        val state = addCatViewModel.state
        val context = LocalContext.current

        // Cat picture
        val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { newUri ->
            newUri?.let { uri ->
                val catPictureName = context.getFileName(uri)

                val input = context.contentResolver.openInputStream(newUri) ?: return@rememberLauncherForActivityResult
                val outputFile = catPictureName?.let {
                    context.filesDir.resolve(it)
                }
                outputFile?.let { input.copyTo(it.outputStream()) }

                val uri = outputFile?.let { it.toUri() }
                uri?.let { addCatViewModel.onEvent(AddCatFormEvent.OnCatProfilePicturePathChanged(it)) }
            }
        }

        // Gender
        val genderOptions = listOf(stringResource(R.string.male), stringResource(R.string.female))
        val genderSelected = state.catGender.ifEmpty { stringResource(R.string.male) }
        val onSelectionChange = { genderPicked: String ->
            addCatViewModel.onEvent(AddCatFormEvent.CatGenderChanged(genderPicked))
        }

        // Neutered
        val neuteredOptions = listOf(stringResource(R.string.yes), stringResource(R.string.no))
        val neuteredSelected = state.catNeutered.ifEmpty { stringResource(R.string.yes) }
        val onNeuteredSelectionChanged = { neuteredValue: String ->
            addCatViewModel.onEvent(AddCatFormEvent.CatNeuteredChanged(neuteredValue))
        }

        // Races
        val catRaces: List<String> = getAllRaces(context)
        var expanded by remember { mutableStateOf(false) }
        var selectedRaceText by remember { mutableStateOf(catRaces[0]) }

        LaunchedEffect(context) {
            addCatViewModel.validationEvents.collect { event ->
                when (event) {
                    is AddCatViewModel.ValidationEvent.Success -> navController.navigateUp()
                }
            }
        }

        Column(
            modifier = Modifier.padding(content).fillMaxSize().padding(16.dp).verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
        ) {
            //section picture
            Image(
                modifier = Modifier.size(100.dp).align(Alignment.CenterHorizontally).clip(CircleShape)
                    .background(Color.LightGray).clickable { launcher.launch("image/*") },
                contentScale = ContentScale.Crop,
                painter = rememberAsyncImagePainter(
                    model = ImageRequest.Builder(context)
                        .data(state.catPictureUri)
                        .build()
                ),
                contentDescription = stringResource(R.string.uri_cat_picture)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // section gender
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                genderOptions.forEach { genderPickedText ->
                    Text(
                        text = genderPickedText,
                        color = Color.White,
                        modifier = Modifier.clip(RoundedCornerShape(size = 12.dp))
                            .clickable { onSelectionChange(genderPickedText) }.background(
                                if (genderPickedText == genderSelected) {
                                    MaterialTheme.colors.primary
                                } else {
                                    Color.LightGray
                                }
                            ).padding(
                                vertical = 12.dp, horizontal = 16.dp
                            )
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // section name
            TextField(
                value = state.catName,
                onValueChange = {
                    addCatViewModel.onEvent(
                        AddCatFormEvent.CatNameChanged(it)
                    )
                },
                label = { Text(stringResource(R.string.cat_name)) },
                isError = state.catNameError != null,
                modifier = Modifier.fillMaxWidth().testTag(TestTags.CAT_NAME_TEXT_FIELD),
                placeholder = {
                    Text(stringResource(R.string.cat_name))
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                singleLine = true
            )

            if (state.catNameError != null) {
                Text(
                    text = state.catNameError.asString(context),
                    color = MaterialTheme.colors.error,
                    modifier = Modifier.align(Alignment.End)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // section birthdate
            Button(
                onClick = {
                    showPickerDate(
                        context.findActivity()!!, AddCatDateEvent.OnBirthdayChanged(0L), addCatViewModel
                    )
                }, modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.add_birthdate))
            }

            if (state.catBirthdate != 0L && state.catBirthdate < DateFormatter.getDefaultDateInMillis()) {
                Spacer(modifier = Modifier.height(5.dp))

                DateFormatter.longToString(state.catBirthdate, context)?.let {
                    TextField(
                        readOnly = true,
                        value = it,
                        onValueChange = { },
                        label = { Text(stringResource(R.string.birthdate)) },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            if (state.catBirthdateError != null) {
                Text(
                    text = state.catBirthdateError.asString(context),
                    color = MaterialTheme.colors.error,
                    modifier = Modifier.align(Alignment.End)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // section neutered
            Text(text = stringResource(R.string.neutered), modifier = Modifier.align(Alignment.Start))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                neuteredOptions.forEach { neuteredValuePickedText ->
                    Text(
                        text = neuteredValuePickedText,
                        color = Color.White,
                        modifier = Modifier.clip(RoundedCornerShape(size = 12.dp))
                            .clickable { onNeuteredSelectionChanged(neuteredValuePickedText) }.background(
                                if (neuteredValuePickedText == neuteredSelected) {
                                    MaterialTheme.colors.primary
                                } else {
                                    Color.LightGray
                                }
                            ).padding(
                                vertical = 12.dp, horizontal = 16.dp
                            )
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // section race
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }
            ) {
                TextField(modifier = Modifier.fillMaxWidth(),
                    readOnly = true,
                    value = selectedRaceText,
                    onValueChange = { },
                    label = { Text(stringResource(R.string.race)) })

                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    catRaces.forEach { selectedRace ->
                        DropdownMenuItem(onClick = {
                            selectedRaceText = selectedRace
                            expanded = false
                            addCatViewModel.onEvent(AddCatFormEvent.CatRaceChanged(selectedRace))
                        }) {
                            Text(text = selectedRace)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // section weight
            TextField(
                value = state.weight,
                onValueChange = {
                    addCatViewModel.onEvent(AddCatFormEvent.CatWeightChanged(it))
                },
                label = { Text(stringResource(R.string.weight)) },
                isError = state.weightError != null,
                modifier = Modifier.fillMaxWidth().testTag(TestTags.CAT_WEIGHT_FIELD),
                placeholder = {
                    Text(stringResource(R.string.weight))
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true
            )

            if (state.weightError != null) {
                Text(
                    text = state.weightError.asString(context),
                    color = MaterialTheme.colors.error,
                    modifier = Modifier.align(Alignment.End)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // section coat
            TextField(
                value = state.catCoat,
                onValueChange = {
                    addCatViewModel.onEvent(
                        AddCatFormEvent.CatCoatChanged(it)
                    )
                },
                label = { Text(stringResource(R.string.coat_placeholder)) },
                isError = state.catCoatError != null,
                modifier = Modifier.fillMaxWidth().testTag(TestTags.CAT_COAT_FIELD),
                placeholder = {
                    Text(stringResource(R.string.coat_placeholder))
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                singleLine = true
            )

            if (state.catCoatError != null) {
                Text(
                    text = state.catCoatError.asString(context),
                    color = MaterialTheme.colors.error,
                    modifier = Modifier.align(Alignment.End)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // section vaccine
            Button(
                onClick = {
                    showPickerDate(
                        context.findActivity()!!, AddCatDateEvent.OnVaccineChanged(0L), addCatViewModel
                    )
                }, modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.add_vaccine))
            }

            if (state.catVaccineDate != 0L && state.catVaccineDate < DateFormatter.getDefaultDateInMillis()) {
                Spacer(modifier = Modifier.height(5.dp))

                DateFormatter.longToString(state.catVaccineDate, context)?.let {
                    TextField(
                        readOnly = true,
                        value = it,
                        onValueChange = { },
                        label = { Text(stringResource(R.string.last_vaccine)) },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            if (state.catVaccineDateError != null) {
                Text(
                    text = state.catVaccineDateError.asString(context),
                    color = MaterialTheme.colors.error,
                    modifier = Modifier.align(Alignment.End)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // section flea
            Button(
                onClick = {
                    showPickerDate(
                        context.findActivity()!!, AddCatDateEvent.OnFleaChanged(0L), addCatViewModel
                    )
                }, modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.add_flea), textAlign = TextAlign.Center)
            }

            if (state.catFleaDate != 0L && state.catFleaDate < DateFormatter.getDefaultDateInMillis()) {
                Spacer(modifier = Modifier.height(5.dp))

                DateFormatter.longToString(state.catFleaDate, context)?.let {
                    TextField(
                        readOnly = true,
                        value = it,
                        onValueChange = { },
                        label = { Text(stringResource(R.string.last_flea_treatment)) },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            if (state.catFleaDateError != null) {
                Text(
                    text = state.catFleaDateError.asString(context),
                    color = MaterialTheme.colors.error,
                    modifier = Modifier.align(Alignment.End)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // section deworming
            Button(
                onClick = {
                    showPickerDate(
                        context.findActivity()!!, AddCatDateEvent.OnDewormingChanged(0L), addCatViewModel
                    )
                }, modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.add_deworming), textAlign = TextAlign.Center)
            }

            if (state.catDewormingDate != 0L && state.catDewormingDate < DateFormatter.getDefaultDateInMillis()) {
                Spacer(modifier = Modifier.height(5.dp))

                DateFormatter.longToString(state.catDewormingDate, context)?.let {
                    TextField(
                        readOnly = true,
                        value = it,
                        onValueChange = { },
                        label = { Text(stringResource(R.string.last_de_worming)) },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            if (state.catDewormingDateError != null) {
                Text(
                    text = state.catDewormingDateError.asString(context),
                    color = MaterialTheme.colors.error,
                    modifier = Modifier.align(Alignment.End)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // section diseases
            TextField(
                value = state.catDiseases,
                onValueChange = {
                    addCatViewModel.onEvent(AddCatFormEvent.CatDiseasesChanged(it))
                },
                label = { Text(stringResource(R.string.diseases)) },
                isError = state.catDiseasesError != null,
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(stringResource(R.string.diseases))
                },
                singleLine = false,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )

            if (state.catDiseasesError != null) {
                Text(
                    text = state.catDiseasesError.asString(context),
                    color = MaterialTheme.colors.error,
                    modifier = Modifier.align(Alignment.End)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // section submit
            Button(
                modifier = Modifier.align(Alignment.End).testTag(TestTags.SUBMIT_CAT_BUTTON),
                onClick = {
                    Log.d("XXXX", "Button clicked")
                    addCatViewModel.onEvent(AddCatFormEvent.Submit)
                }
            ) {
                Text(
                    text = stringResource(R.string.submit_cat)
                )
            }

            BackHandler {
                addCatViewModel.onEvent(AddCatFormEvent.OnBackPressed)
                navController.navigateUp()
            }
        }
    }
}

fun Context.findActivity(): AppCompatActivity? = when (this) {
    is AppCompatActivity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}

private fun getAllRaces(context: Context): List<String> = listOf(
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

private fun showPickerDate(activity: AppCompatActivity, type: AddCatDateEvent, addCatViewModel: AddCatViewModel) {
    val picker = MaterialDatePicker.Builder.datePicker().setSelection(DateFormatter.getDefaultDateInMillis()).build()

    picker.show(activity.supportFragmentManager, picker.toString())
    picker.addOnPositiveButtonClickListener {
        picker.selection?.let { selectedDate ->
            addCatViewModel.onDateEvent(type, selectedDate)
        }
    }
}