package com.pm.challengeapp.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pm.challengeapp.R
import com.pm.challengeapp.data.local.model.GeneratedString

@Composable
fun GeneratedStringItem(
    item: GeneratedString,
    onDeleteOne: (GeneratedString) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text("String: ${item.content}")
            Text("Length: ${item.length}")
            Text("Created: ${item.createdAt}")
            Button(
                onClick = { onDeleteOne(item) },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text(stringResource(id = R.string.delete_button))  // Use string resource here
            }
        }
    }
}
