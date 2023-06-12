package com.example.quotesapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.quotesapp.model.QuoteItem

@Database(entities = [QuoteItem::class], version = 1, exportSchema = false)
abstract class ApplicationDatabase: RoomDatabase() {
    abstract fun QuoteDao() : QuoteDataBaseDao
}