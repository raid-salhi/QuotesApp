package com.example.quotesapp.repository

import com.example.quotesapp.data.QuoteDataBaseDao
import com.example.quotesapp.model.QuoteItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import okhttp3.Dispatcher
import javax.inject.Inject

class DatabaseRepository @Inject constructor(private val quoteDataBaseDao: QuoteDataBaseDao) {
    fun getAllSavedQuotes () : Flow<List<QuoteItem>> = quoteDataBaseDao.getAllSavedQuotes().flowOn(
        Dispatchers.IO).conflate()
    suspend fun addSavedQuote(quoteItem: QuoteItem)=quoteDataBaseDao.addSavedQuote(quoteItem)
    suspend fun deleteSavedQuote(quoteItem: QuoteItem)=quoteDataBaseDao.deleteSavedQuote(quoteItem)
}