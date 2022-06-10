package com.ms.catlife.feature_main.presentation.screen.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ms.catlife.feature_add_cat.domain.util.CatOrder

@Composable
fun OrderSection(
    catOrder: CatOrder,
    onOrderChanged: (CatOrder) -> Unit
) {
    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp)) {
        DefaultRadioButton(
            text = "Name",
            optionSelected = catOrder is CatOrder.Name,
            onSelect = { onOrderChanged(CatOrder.Name(catOrder.orderType)) }
        )
        Spacer(modifier = Modifier.width(8.dp))

        DefaultRadioButton(
            text = "Age",
            optionSelected = catOrder is CatOrder.Age,
            onSelect = { onOrderChanged(CatOrder.Age(catOrder.orderType)) }
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}