package com.example.quotesapp.di

import com.example.quotesapp.network.ApiService
import com.example.quotesapp.utils.Constants
import dagger.Provides
import dagger.hilt.InstallIn
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
}