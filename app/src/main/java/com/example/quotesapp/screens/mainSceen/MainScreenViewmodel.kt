package com.example.quotesapp.screens.mainSceen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quotesapp.model.Quote
import com.example.quotesapp.model.QuoteItem
import com.example.quotesapp.repository.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewmodel @Inject constructor(private val repository: ApiRepository) : ViewModel() {

    val data : MutableState<Quote> = mutableStateOf(Quote())
    init {
        viewModelScope.launch(Dispatchers.IO) {
            data.value=repository.getRandomQuote()
        }
    }
}