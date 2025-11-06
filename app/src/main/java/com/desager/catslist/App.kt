package com.desager.catslist

import android.app.Application
import com.desager.catslist.data.remote.api.CatsApi
import com.desager.catslist.data.remote.interceptors.ApiKeyInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class App : Application() {

    lateinit var catsApi: CatsApi

    override fun onCreate() {
        super.onCreate()

        catsApi = provideApi()
    }

    private fun provideApi(): CatsApi {
        val client = OkHttpClient.Builder()
            .addInterceptor(ApiKeyInterceptor())
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()

        return Retrofit.Builder()
            .baseUrl("https://api.thecatapi.com")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create()
    }
}