package com.example.quotesapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.quotesapp.model.QuoteItem
import kotlinx.coroutines.flow.Flow

@Dao
interface QuoteDataBaseDao {
    @Query("SELECT * from q_tbl")
    fun getAllSavedQuotes(): Flow<List<QuoteItem>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addSavedQuote(quoteItem: QuoteItem)
    @Delete
    suspend fun deleteSavedQuote(quoteItem: QuoteItem)
}