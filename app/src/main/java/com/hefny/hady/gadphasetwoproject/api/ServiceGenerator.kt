package com.hefny.hady.gadphasetwoproject.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServiceGenerator {
    companion object {
        private const val BASE_URL = "https://gadsapi.herokuapp.com"

        private val builder: Retrofit.Builder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())

        private fun getInstance(): Retrofit {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.HEADERS
            logging.level = HttpLoggingInterceptor.Level.BODY
            val client: OkHttpClient = OkHttpClient.Builder()
                .addNetworkInterceptor(logging).build()
            return builder.client(client).build()
        }

        private val gadsApi: GadsApi = getInstance().create(GadsApi::class.java)

        fun getGadsApi(): GadsApi {
            return gadsApi
        }
    }
}