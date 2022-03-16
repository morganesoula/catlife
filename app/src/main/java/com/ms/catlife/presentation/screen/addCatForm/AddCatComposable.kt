package com.ms.catlife.presentation.screen.addCatForm

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.material.datepicker.MaterialDatePicker
import com.ms.catlife.R
import com.ms.catlife.presentation.screen.util.ErrorComposable
import com.ms.catlife.theme.CatLifeTheme
import com.ms.catlife.theme.Shapes
import com.ms.catlife.util.DateFormatter

@SuppressLint("RememberReturnType")
@Composable
fun CatFormBody(
    addCatViewModel: AddCatViewModel = viewModel(), contentPadding: PaddingValues
) {

    val context = LocalContext.current
    val formState = remember { addCatViewModel.formState }

    val catNameState = formState.getState(stringResource(R.string.name_of_the_cat))
    val weightState = formState.getState(stringResource(R.string.weight))
    val raceState = formState.getState(stringResource(R.string.race))
    val coatState = formState.getState(stringResource(R.string.coat))
    val diseasesState = formState.getState(stringResource(R.string.diseases))

    val selectedOption = remember { mutableStateOf(context.getString(R.string.male)) }
    var birthdateError: Boolean by remember { mutableStateOf(false) }
    var lastVaccineError: Boolean by remember { mutableStateOf(false) }
    var lastDewormingError: Boolean by remember { mutableStateOf(false) }
    var lastFleaError: Boolean by remember { mutableStateOf(false) }

    var birthdatePicked: String? by remember { mutableStateOf(null) }
    val updatedBirthdate = { date: Long? -> birthdatePicked = DateFormatter.longToString(date, context) }

    var vaccineDatePicked: String? by remember { mutableStateOf(null) }
    val updatedVaccineDate = { date: Long? -> vaccineDatePicked = DateFormatter.longToString(date, context) }

    var dewormingDatePicked: String? by remember { mutableStateOf(null) }
    val updatedDewormingDate = { date: Long? -> dewormingDatePicked = DateFormatter.longToString(date, context) }

    var fleaDatePicked: String? by remember { mutableStateOf(null) }
    val updatedFleaDate = { date: Long? -> fleaDatePicked = DateFormatter.longToString(date, context) }

    CatLifeTheme {
        Column(modifier = Modifier.padding(contentPadding)) {
            val focusManager = LocalFocusManager.current

            Column(
                modifier = Modifier.weight(1.0f, false).verticalScroll(rememberScrollState())
            ) {
                Row(
                    modifier = Modifier.selectableGroup().padding(0.dp, 10.dp, 0.dp, 0.dp).fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_baseline_male_80),
                            contentDescription = stringResource(R.string.male)
                        )
                        RadioButton(selected = selectedOption.value == stringResource(R.string.male),
                            onClick = { selectedOption.value = context.getString(R.string.male) })
                    }
                    Spacer(modifier = Modifier.size(16.dp))
                    Column(
                        verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_baseline_female_80),
                            contentDescription = stringResource(R.string.female)
                        )
                        RadioButton(selected = selectedOption.value == stringResource(R.string.female),
                            onClick = { selectedOption.value = context.getString(R.string.female) })
                    }
                }

                AppTextField(
                    modifier = Modifier.fillMaxWidth().padding(4.dp),
                    text = catNameState.text,
                    onChange = { catNameState.change(it) },
                    label = { Text(stringResource(R.string.name_of_the_cat)) },
                    placeholder = stringResource(R.string.name_of_the_cat),
                    keyboardType = KeyboardType.Text,
                    isError = catNameState.hasError
                )

                if (catNameState.hasError) ErrorComposable(catNameState.errorMessage)

                DatePickerView(
                    birthdatePicked,
                    context.getString(R.string.birthdate),
                    updatedBirthdate,
                    isError = birthdateError
                )

                if (birthdatePicked.isNullOrEmpty()) {
                    birthdateError = true
                    ErrorComposable(null)
                } else if (birthdatePicked!! == stringResource(R.string.inconsistent)) {
                    birthdateError = true
                    ErrorComposable(null)
                } else {
                    birthdateError = false
                }

                AppTextField(
                    modifier = Modifier.fillMaxWidth().padding(4.dp),
                    label = { Text(text = stringResource(R.string.weight)) },
                    text = weightState.text,
                    placeholder = stringResource(R.string.weight),
                    onChange = {
                        weightState.change(it)
                    },
                    keyboardType = KeyboardType.Number,
                    keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) }),
                    isError = weightState.hasError
                )

                if (weightState.hasError) ErrorComposable(weightState.errorMessage)

                AppTextField(
                    modifier = Modifier.fillMaxWidth().padding(4.dp),
                    label = { Text(text = stringResource(R.string.race)) },
                    text = raceState.text,
                    onChange = { raceState.change(it) },
                    placeholder = stringResource(R.string.race),
                    keyboardType = KeyboardType.Text,
                    keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) }),
                    isError = raceState.hasError
                )

                if (raceState.hasError) ErrorComposable(raceState.errorMessage)

                AppTextField(
                    modifier = Modifier.fillMaxWidth().padding(4.dp),
                    label = { Text(text = stringResource(R.string.coat)) },
                    text = coatState.text,
                    onChange = { coatState.change(it) },
                    placeholder = stringResource(R.string.coat_placeholder),
                    keyboardType = KeyboardType.Text,
                    keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) }),
                    isError = coatState.hasError
                )

                if (coatState.hasError) ErrorComposable(coatState.errorMessage)

                AppTextField(
                    modifier = Modifier.fillMaxWidth().padding(4.dp),
                    label = { Text(text = stringResource(R.string.diseases)) },
                    text = diseasesState.text,
                    onChange = { diseasesState.change(it) },
                    keyboardType = KeyboardType.Text,
                    placeholder = stringResource(R.string.diseases_placeholder),
                    singleLine = false
                )

                DatePickerView(
                    vaccineDatePicked,
                    context.getString(R.string.last_vaccine),
                    updatedVaccineDate,
                    lastVaccineError
                )

                vaccineDatePicked?.let { lastVaccineError = it == stringResource(R.string.inconsistent) }

                DatePickerView(
                    dewormingDatePicked,
                    context.getString(R.string.last_de_worming),
                    updatedDewormingDate,
                    lastDewormingError
                )

                dewormingDatePicked?.let { lastDewormingError = it == stringResource(R.string.inconsistent) }

                DatePickerView(
                    fleaDatePicked,
                    context.getString(R.string.last_flea_treatment),
                    updatedFleaDate,
                    lastFleaError
                )

                fleaDatePicked?.let { lastFleaError = it == stringResource(R.string.inconsistent) }

                Button(
                    onClick = { },
                    modifier = Modifier.align(Alignment.CenterHorizontally).padding(0.dp, 20.dp, 0.dp, 10.dp),
                    enabled = formState.validate() && !birthdateError && !lastVaccineError && !lastDewormingError && !lastFleaError
                ) {
                    Text(stringResource(R.string.add_cat_submit))
                }
            }
        }
    }
}

@Composable
private fun AppTextField(
    modifier: Modifier = Modifier,
    text: String,
    placeholder: String,
    label: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    onChange: (String) -> Unit = {},
    keyboardType: KeyboardType = KeyboardType.Text,
    keyboardActions: KeyboardActions = KeyboardActions(),
    isEnabled: Boolean = true,
    singleLine: Boolean = true,
    isError: Boolean = false,
) {
    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        label = label,
        value = text,
        onValueChange = onChange,
        leadingIcon = leadingIcon,
        textStyle = TextStyle(fontSize = 18.sp),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        keyboardActions = keyboardActions,
        enabled = isEnabled,
        placeholder = {
            Text(text = placeholder)
        },
        singleLine = singleLine,
        colors = if (isError) {
            TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = Color.Red)
            TextFieldDefaults.textFieldColors(
                disabledLabelColor = Color.Black, backgroundColor = Color.White, unfocusedLabelColor = Color.Black
            )
        } else {
            TextFieldDefaults.textFieldColors(
                disabledLabelColor = Color.Black, backgroundColor = Color.White, unfocusedLabelColor = Color.Black
            )
        },
        isError = isError
    )
}

@Composable
private fun DatePickerView(
    datePicked: String?,
    datePickerType: String,
    updatedDate: (date: Long?) -> Unit,
    isError: Boolean = false
) {
    val activity = LocalContext.current as AppCompatActivity

    Box(modifier = if (isError) {
        Modifier.fillMaxWidth().padding(4.dp).wrapContentSize(Alignment.TopStart).padding(top = 10.dp)
            .border(1.dp, color = Color.Red, shape = Shapes.medium).clickable {
                showDatePicker(activity, updatedDate)
            }
    } else {
        Modifier.fillMaxWidth().padding(4.dp).wrapContentSize(Alignment.TopStart).padding(top = 10.dp)
            .border(1.dp, MaterialTheme.colors.onSurface.copy(alpha = 0.5f), shape = Shapes.medium).clickable {
                showDatePicker(activity, updatedDate)
            }
    }) {
        ConstraintLayout(
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        ) {
            val (label, iconView) = createRefs()

            Text(text = datePicked ?: datePickerType,
                color = Color.Black,
                modifier = Modifier.fillMaxWidth().constrainAs(label) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(iconView.start)
                    width = Dimension.fillToConstraints
                })

            Icon(
                imageVector = Icons.Default.DateRange,
                contentDescription = null,
                modifier = Modifier.size(20.dp, 20.dp).constrainAs(iconView) {
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                },
                tint = MaterialTheme.colors.onSurface
            )
        }
    }
}

private fun showDatePicker(
    activity: AppCompatActivity, updatedDate: (Long?) -> Unit
) {
    val picker = MaterialDatePicker.Builder.datePicker().build()
    picker.show(activity.supportFragmentManager, picker.toString())

    picker.addOnPositiveButtonClickListener {
        updatedDate(it)
    }
}




