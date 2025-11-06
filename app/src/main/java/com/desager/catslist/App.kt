package com.desager.catslist

import android.app.Application
import androidx.room.Room
import com.desager.catslist.data.local.CatDatabase
import com.desager.catslist.data.local.dao.CatsDao
import com.desager.catslist.data.remote.api.CatsApi
import com.desager.catslist.data.remote.interceptors.ApiKeyInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class App : Application() {

    lateinit var catsApi: CatsApi
    lateinit var catsDao: CatsDao

    override fun onCreate() {
        super.onCreate()

        catsApi = provideApi()
        catsDao = provideDao()
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

    private fun provideDao(): CatsDao {
        return Room.databaseBuilder(
            applicationContext,
            CatDatabase::class.java,
            "cat.db"
        ).build().catsDao
    }
}