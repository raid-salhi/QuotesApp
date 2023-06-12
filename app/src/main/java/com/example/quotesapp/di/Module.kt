package com.example.quotesapp.di

import android.content.Context
import androidx.room.Room
import com.example.quotesapp.data.ApplicationDatabase
import com.example.quotesapp.data.QuoteDataBaseDao
import com.example.quotesapp.network.ApiService
import com.example.quotesapp.utils.Constants
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@dagger.Module
@InstallIn(SingletonComponent::class)
class Module  {
    @Provides
    @Singleton
    fun provideRetrofit () : ApiService = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiService::class.java)
    @Provides
    @Singleton
    fun provideDatabase (@ApplicationContext context: Context) : ApplicationDatabase =
        Room.databaseBuilder(
            context,
            ApplicationDatabase::class.java,
            "q_db",
        ).fallbackToDestructiveMigration()
            .build()
    @Provides
    @Singleton
    fun provideQuoteDao(applicationDatabase: ApplicationDatabase):QuoteDataBaseDao=applicationDatabase.QuoteDao()
}