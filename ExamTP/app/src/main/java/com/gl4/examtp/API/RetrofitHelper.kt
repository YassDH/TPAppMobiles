package com.gl4.examtp.API

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    private const val BASE_URL = "https://imdb-top-100-movies.p.rapidapi.com/"
    //headers
    private const val HEADER_API_KEY = "3ff9f43460mshfa4faefc2b70jsn0d1c3d5204fd"
    //3ff9f43460mshfa4faefc2b75630p17e930jsn0d1c3d5204fd
    private const val HEADER_HOST = "imdb-top-100-movies.p.rapidapi.com"
    /**
     * The OkHttpClient with headers added.
     */
    private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
                .header("X-RapidAPI-Key", HEADER_API_KEY)
                .header("X-RapidAPI-Host", HEADER_HOST)
                // Add more headers if needed
                .method(original.method(), original.body())
            val request = requestBuilder.build()
            chain.proceed(request)
        }
        .build()
    /**
     * The Retrofit object with Gson converter and custom OkHttpClient.
     */
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    /**
     * A public Api object that exposes the lazy-initialized Retrofit service
     */
    val retrofitService : MoviesAPI by lazy { retrofit.create(MoviesAPI::class.java) }
}