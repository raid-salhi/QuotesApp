package com.example.quotesapp.network

import com.example.quotesapp.model.Quote
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface ApiService {

    @GET("quotes")
    suspend fun getRandomQuote(): Quote
}