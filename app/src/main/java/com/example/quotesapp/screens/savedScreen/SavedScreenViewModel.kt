package com.example.quotesapp.screens.savedScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quotesapp.model.QuoteItem
import com.example.quotesapp.repository.DatabaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class SavedScreenViewModel @Inject constructor(private val databaseRepository: DatabaseRepository) : ViewModel() {
    private val _data = MutableStateFlow<List<QuoteItem>>(emptyList())
    val data= _data.asStateFlow()
    init {
        viewModelScope.launch (Dispatchers.IO) {
            databaseRepository.getAllSavedQuotes().distinctUntilChanged().collect {
                _data.value=it
            }
        }
    }
    fun addQuote(quoteItem: QuoteItem) {
        viewModelScope.launch {
            databaseRepository.addSavedQuote(quoteItem)
        }
    }
    fun deleteQuote(quoteItem: QuoteItem) {
        viewModelScope.launch {
            databaseRepository.deleteSavedQuote(quoteItem)
        }
    }
}