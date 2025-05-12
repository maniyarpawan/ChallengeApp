package com.pm.challengeapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.pm.challengeapp.R

@Composable
fun ActionButtons(
    onGenerateClicked: () -> Unit,
    onDeleteAll: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(onClick = onGenerateClicked) {
            Text(stringResource(id = R.string.generate_button))  // Use string resource here
        }

        Button(onClick = onDeleteAll) {
            Text(stringResource(id = R.string.clear_all_button))  // Use string resource here
        }
    }
}
