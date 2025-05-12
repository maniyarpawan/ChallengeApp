package com.pm.challengeapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import com.pm.challengeapp.ui.screens.RandomStringAppUI
import com.pm.challengeapp.ui.theme.ChallengeAppTheme
import com.pm.challengeapp.viewmodel.StringViewModel

class MainActivity : ComponentActivity() {

    private val viewModel: StringViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val uiState = viewModel.uiState.collectAsState().value
            val error = viewModel.errorMessage.collectAsState().value
            ChallengeAppTheme {
                RandomStringAppUI(
                    generatedStrings = uiState,
                    onGenerateClicked = { length -> viewModel.queryAndInsert(length) },
                    onDeleteAll = { viewModel.deleteAll() },
                    onDeleteOne = { viewModel.delete(it) },
                    errorMessage = error,
                    onDismissError = { viewModel.clearError() }
                )
            }
        }
    }
}
