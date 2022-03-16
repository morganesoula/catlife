package com.ms.catlife.presentation.screen.addCatForm

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dsc.form_builder.FormState
import com.dsc.form_builder.TextFieldState
import com.dsc.form_builder.Validators
import com.ms.catlife.R
import com.ms.catlife.domain.model.Cat
import com.ms.catlife.domain.useCase.CatUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@SuppressLint("StaticFieldLeak")
@HiltViewModel
class AddCatViewModel @Inject constructor(
    @ApplicationContext val application: Context,
    private val catUseCases: CatUseCases
) : ViewModel() {

    var formState = FormState(
        fields = listOf(
            TextFieldState<String>(
                name = application.getString(R.string.name_of_the_cat),
                validators = listOf(
                    Validators.Required(application.getString(R.string.should_not_be_empty))
                )
            ),
            TextFieldState(
                name = application.getString(R.string.weight),
                transform = { it.toFloat() },
                validators = listOf(
                    Validators.Required(),
                    Validators.MinValue(0.1.toInt(), application.getString(R.string.weight_error_message)),
                    Validators.MaxValue(currentLocale(), application.getString(R.string.weight_error_message))
                )
            ),
            TextFieldState<String>(
                name = application.getString(R.string.race),
                validators = listOf(Validators.Required(application.getString(R.string.should_not_be_empty)))
            ),
            TextFieldState<String>(
                name = application.getString(R.string.coat),
                validators = listOf(Validators.Required(application.getString(R.string.should_not_be_empty)))
            ),
            TextFieldState<String>(
                name = application.getString(R.string.diseases)
            )
        )
    )

    private fun currentLocale(): Int = if (Locale.getDefault().language != Locale.FRENCH.toString()) 49 else 22

    fun insertCat(cat: Cat) =
        viewModelScope.launch {
            catUseCases.insertCatUseCase.invoke(cat)
        }
}