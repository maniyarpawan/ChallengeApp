package com.pm.challengeapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pm.challengeapp.data.local.model.GeneratedString
import com.pm.challengeapp.ui.components.ActionButtons
import com.pm.challengeapp.ui.components.ErrorSnackbar
import com.pm.challengeapp.ui.components.GeneratedStringItem
import com.pm.challengeapp.ui.components.LoadingIndicator
import com.pm.challengeapp.ui.components.MaxLengthInput
import kotlinx.coroutines.launch

@Composable
fun RandomStringAppUI(
    generatedStrings: List<GeneratedString>,
    onGenerateClicked: (Int) -> Unit,
    onDeleteAll: () -> Unit,
    onDeleteOne: (GeneratedString) -> Unit,
    errorMessage: String?,
    onDismissError: () -> Unit
) {
    var inputLength = remember { mutableStateOf("10") }
    val isLoading = remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    ErrorSnackbar(errorMessage, snackbarHostState, onDismissError)

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
        ) {
            // Max Length Input Field
            MaxLengthInput(
                value = inputLength.value,
                onValueChange = { inputLength.value = it }
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Action Buttons (Generate and Clear All)
            ActionButtons(
                onGenerateClicked = {
                    val len = inputLength.value.toIntOrNull() ?: 10
                    isLoading.value = true
                    coroutineScope.launch {
                        onGenerateClicked(len)
                        isLoading.value = false
                    }
                },
                onDeleteAll = onDeleteAll
            )

            // Loading Indicator
            LoadingIndicator(isLoading = isLoading.value)

            Spacer(modifier = Modifier.height(8.dp))

            // List of Generated Strings
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                itemsIndexed(generatedStrings) { _, item ->
                    GeneratedStringItem(
                        item = item,
                        onDeleteOne = onDeleteOne
                    )
                }
            }
        }
    }
}
