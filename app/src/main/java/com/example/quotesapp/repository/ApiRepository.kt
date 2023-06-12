package com.example.quotesapp.repository

import com.example.quotesapp.model.Quote
import com.example.quotesapp.model.QuoteItem
import com.example.quotesapp.network.ApiService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ApiRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun getRandomQuote(): Quote = apiService.getRandomQuote()
}