package com.example.quotesapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity("q_tbl")
data class QuoteItem(
    @ColumnInfo
    @SerializedName("author")
    val a: String,
    @PrimaryKey
    @SerializedName("id")
    val c: String,
//    @ColumnInfo
//    val h: String,
    @ColumnInfo
    @SerializedName("quote")
    val q: String
)