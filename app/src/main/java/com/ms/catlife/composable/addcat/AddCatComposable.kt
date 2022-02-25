package com.ms.catlife.composable.addcat

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.ms.catlife.R
import com.ms.catlife.addcat.viewmodel.AddCatViewModel
import com.ms.catlife.theme.CatLifeTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.ms.catlife.theme.PrimaryTextColor

@Composable
fun AddCatFormBody(contentPadding: PaddingValues, addCatViewModel: AddCatViewModel) {
    CatForm(contentPadding, addCatViewModel)
}

@Composable
private fun CatForm(contentPadding: PaddingValues, addCatViewModel: AddCatViewModel) {
    val selectedOption = remember { mutableStateOf("") }
    var nameValue by remember { mutableStateOf(TextFieldValue("")) }
    var birthdateDayValue by remember { mutableStateOf(TextFieldValue("")) }
    var birthdateMonthValue by remember { mutableStateOf(TextFieldValue("")) }
    var birthdateYearValue by remember { mutableStateOf(TextFieldValue("")) }
    var weightValue by remember { mutableStateOf(TextFieldValue("")) }
    var raceValue by remember { mutableStateOf(TextFieldValue("")) }
    var coatValue by remember { mutableStateOf(TextFieldValue("")) }
    var vaccineDayValue by remember { mutableStateOf(TextFieldValue("")) }
    var vaccineMonthValue by remember { mutableStateOf(TextFieldValue("")) }
    var vaccineYearValue by remember { mutableStateOf(TextFieldValue("")) }
    var dewormingDayValue by remember { mutableStateOf(TextFieldValue("")) }
    var dewormingMonthValue by remember { mutableStateOf(TextFieldValue("")) }
    var dewormingYearValue by remember { mutableStateOf(TextFieldValue("")) }
    var fleaDayValue by remember { mutableStateOf(TextFieldValue("")) }
    var fleaMonthValue by remember { mutableStateOf(TextFieldValue("")) }
    var fleaYearValue by remember { mutableStateOf(TextFieldValue("")) }
    var knownDiseasesValue by remember { mutableStateOf(TextFieldValue("")) }

    CatLifeTheme {
        Column(modifier = Modifier.padding(contentPadding)) {
            Column(
                modifier = Modifier.weight(1.0f, false).verticalScroll(rememberScrollState())
            ) {
                OutlinedTextField(
                    value = nameValue,
                    onValueChange = { nameValue = it },
                    label = { Text(stringResource(R.string.name_of_the_cat)) },
                    modifier = Modifier.fillMaxWidth().padding(4.dp),
                )

                Text(stringResource(R.string.gender), modifier = Modifier.padding(2.dp, 15.dp, 0.dp, 0.dp))
                Row(
                    Modifier.selectableGroup().padding(0.dp, 10.dp, 0.dp, 0.dp).fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = selectedOption.value == stringResource(R.string.male),
                        onClick = { selectedOption.value = "Male" }
                    )
                    Icon(
                        painter = painterResource(R.drawable.outline_male_24),
                        contentDescription = stringResource(R.string.male)
                    )
                    Spacer(modifier = Modifier.size(16.dp))
                    RadioButton(
                        selected = selectedOption.value == stringResource(R.string.female),
                        onClick = { selectedOption.value = "Female" }
                    )
                    Icon(
                        painter = painterResource(R.drawable.outline_female_24),
                        contentDescription = stringResource(R.string.female)
                    )
                }

                Text(stringResource(R.string.birthdate), modifier = Modifier.padding(2.dp, 15.dp, 0.dp, 0.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    OutlinedTextField(
                        modifier = Modifier.weight(2f).padding(4.dp),
                        value = birthdateDayValue,
                        onValueChange = { birthdateDayValue = it },
                        label = { Text(stringResource(R.string.day)) },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                    OutlinedTextField(
                        modifier = Modifier.weight(2f).padding(4.dp),
                        value = birthdateMonthValue,
                        onValueChange = { birthdateMonthValue = it },
                        label = { Text(stringResource(R.string.month)) },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                    OutlinedTextField(
                        modifier = Modifier.weight(2f).padding(4.dp),
                        value = birthdateYearValue,
                        onValueChange = { birthdateYearValue = it },
                        label = { Text(stringResource(R.string.year)) },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                }

                OutlinedTextField(
                    value = weightValue,
                    onValueChange = { weightValue = it },
                    label = { Text(stringResource(R.string.weight)) },
                    modifier = Modifier.fillMaxWidth().padding(4.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )

                OutlinedTextField(
                    value = raceValue,
                    onValueChange = { raceValue = it },
                    label = { Text(stringResource(R.string.race)) },
                    modifier = Modifier.fillMaxWidth().padding(4.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                )

                OutlinedTextField(
                    value = coatValue,
                    onValueChange = { coatValue = it },
                    label = { Text(stringResource(R.string.coat)) },
                    placeholder = { Text(stringResource(R.string.coat_placeholder)) },
                    modifier = Modifier.fillMaxWidth().padding(4.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                )

                OutlinedTextField(
                    value = knownDiseasesValue,
                    onValueChange = { knownDiseasesValue = it },
                    label = { Text(stringResource(R.string.diseases)) },
                    modifier = Modifier.fillMaxWidth().padding(4.dp),
                    singleLine = false
                )

                Text(stringResource(R.string.last_vaccine), modifier = Modifier.padding(2.dp, 15.dp, 0.dp, 0.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    OutlinedTextField(
                        modifier = Modifier.weight(2f).padding(4.dp),
                        value = vaccineDayValue,
                        onValueChange = { vaccineDayValue = it },
                        label = { Text(stringResource(R.string.day)) },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                    OutlinedTextField(
                        modifier = Modifier.weight(2f).padding(4.dp),
                        value = vaccineMonthValue,
                        onValueChange = { vaccineMonthValue = it },
                        label = { Text(stringResource(R.string.month)) },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                    OutlinedTextField(
                        modifier = Modifier.weight(2f).padding(4.dp),
                        value = vaccineYearValue,
                        onValueChange = { vaccineYearValue = it },
                        label = { Text(stringResource(R.string.year)) },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                }

                Text(stringResource(R.string.last_de_worming), modifier = Modifier.padding(2.dp, 15.dp, 0.dp, 0.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    OutlinedTextField(
                        modifier = Modifier.weight(2f).padding(4.dp),
                        value = dewormingDayValue,
                        onValueChange = { dewormingDayValue = it },
                        label = { Text(stringResource(R.string.day)) },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                    OutlinedTextField(
                        modifier = Modifier.weight(2f).padding(4.dp),
                        value = dewormingMonthValue,
                        onValueChange = { dewormingMonthValue = it },
                        label = { Text(stringResource(R.string.month)) },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                    OutlinedTextField(
                        modifier = Modifier.weight(2f).padding(4.dp),
                        value = dewormingYearValue,
                        onValueChange = { dewormingYearValue = it },
                        label = { Text(stringResource(R.string.year)) },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                }

                Text(stringResource(R.string.last_flea_treatment), modifier = Modifier.padding(2.dp, 15.dp, 0.dp, 0.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    OutlinedTextField(
                        modifier = Modifier.weight(2f).padding(4.dp),
                        value = fleaDayValue,
                        onValueChange = { fleaDayValue = it },
                        label = { Text(stringResource(R.string.day)) },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                    OutlinedTextField(
                        modifier = Modifier.weight(2f).padding(4.dp),
                        value = fleaMonthValue,
                        onValueChange = { fleaMonthValue = it },
                        label = { Text(stringResource(R.string.month)) },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                    OutlinedTextField(
                        modifier = Modifier.weight(2f).padding(4.dp),
                        value = fleaYearValue,
                        onValueChange = { fleaYearValue = it },
                        label = { Text(stringResource(R.string.year)) },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                }

                Button(
                    enabled = false,
                    onClick = { addCatViewModel.insert(null) },
                    modifier = Modifier.align(Alignment.CenterHorizontally).padding(0.dp, 25.dp, 0.dp, 0.dp)
                ) {
                    Text(stringResource(R.string.add_cat_submit))
                }
            }
        }
    }
}

