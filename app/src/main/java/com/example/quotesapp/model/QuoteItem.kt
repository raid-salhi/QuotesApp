package com.example.quotesapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("q_tbl")
data class QuoteItem(
    @ColumnInfo
    val a: String,
    @PrimaryKey
    val c: String,
    @ColumnInfo
    val h: String,
    @ColumnInfo
    val q: String
)