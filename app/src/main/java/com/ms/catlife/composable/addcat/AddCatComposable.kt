package com.ms.catlife.composable.addcat

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.ms.catlife.R
import com.ms.catlife.theme.CatLifeTheme

@Composable
fun AddCatFormBody() {
    CatForm()
}

@Composable
private fun CatForm() {
    val selectedOption = remember { mutableStateOf("") }

    CatLifeTheme {
        Column {
            OutlinedTextField(
                value = "",
                onValueChange = { },
                label = { Text(stringResource(R.string.name_of_the_cat)) },
                modifier = Modifier.fillMaxWidth().padding(4.dp)
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
                Text(
                    text = stringResource(R.string.male),
                    style = MaterialTheme.typography.body1.merge()
                )
                Spacer(modifier = Modifier.size(16.dp))
                RadioButton(
                    selected = selectedOption.value == stringResource(R.string.female),
                    onClick = { selectedOption.value = "Female" }
                )
                Text(
                    text = stringResource(R.string.female),
                    style = MaterialTheme.typography.body1.merge()
                )
            }

            Text(stringResource(R.string.birthdate), modifier = Modifier.padding(2.dp, 15.dp, 0.dp, 0.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                OutlinedTextField(
                    modifier = Modifier.weight(2f).padding(4.dp),
                    value = "",
                    onValueChange = { },
                    label = { Text(stringResource(R.string.day)) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                OutlinedTextField(
                    modifier = Modifier.weight(2f).padding(4.dp),
                    value = "",
                    onValueChange = { },
                    label = { Text(stringResource(R.string.month)) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                OutlinedTextField(
                    modifier = Modifier.weight(2f).padding(4.dp),
                    value = "",
                    onValueChange = { },
                    label = { Text(stringResource(R.string.year)) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }

            OutlinedTextField(
                value = "",
                onValueChange = { },
                label = { Text(stringResource(R.string.race)) },
                modifier = Modifier.fillMaxWidth().padding(4.dp)
            )
        }
    }
}

