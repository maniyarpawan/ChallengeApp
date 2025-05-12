package com.pm.challengeapp.viewmodel

import android.app.Application
import android.content.ContentResolver
import android.net.Uri
import android.os.Bundle
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.pm.challengeapp.data.local.db.AppDatabase
import com.pm.challengeapp.data.local.model.GeneratedString
import com.pm.challengeapp.data.repository.StringRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class StringViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: StringRepository

    private val _uiState = MutableStateFlow<List<GeneratedString>>(emptyList())
    val uiState: StateFlow<List<GeneratedString>> get() = _uiState

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> get() = _errorMessage

    init {
        val dao = AppDatabase.getDatabase(application).generatedStringDao()
        repository = StringRepository(dao)
        getAllStrings()
    }

    private fun getAllStrings() {
        viewModelScope.launch {
            repository.allStrings.collect { strings ->
                _uiState.value = strings
            }
        }
    }

    fun queryAndInsert(length: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val uri = Uri.parse("content://com.iav.contestdataprovider/text")
                val bundle = Bundle().apply {
                    putInt("maxLength", length)
                    putInt(ContentResolver.QUERY_ARG_LIMIT, 1)
                }
                val cursor =
                    getApplication<Application>().contentResolver.query(uri, null, null, null, null)

                cursor?.use {
                    if (it.moveToFirst()) {
                        val index = it.getColumnIndex("data")
                        if (index != -1) {
                            val data = it.getString(index)
                            val newItem = GeneratedString(
                                content = data,
                                length = data.length,
                                createdAt = System.currentTimeMillis().toString()
                            )
                            repository.insert(newItem)
                        }
                    } else {
                        _errorMessage.value = "No data returned by provider."
                    }
                } ?: run {
                    _errorMessage.value = "Content provider returned null cursor."
                }
            } catch (e: Exception) {
                _errorMessage.value = "Query failed: ${e.message}"
            }
        }
    }


    fun clearError() {
        _errorMessage.value = null
    }

    fun insert(generatedString: GeneratedString) = viewModelScope.launch {
        try {
            repository.insert(generatedString)
        } catch (e: Exception) {
            _errorMessage.value = "Error inserting string: ${e.message}"
        }
    }

    fun delete(item: GeneratedString) = viewModelScope.launch {
        try {
            repository.delete(item)
        } catch (e: Exception) {
            _errorMessage.value = "Error deleting string: ${e.message}"
        }
    }

    fun deleteAll() = viewModelScope.launch {
        try {
            repository.deleteAll()
        } catch (e: Exception) {
            _errorMessage.value = "Error deleting all strings: ${e.message}"
        }
    }
}
