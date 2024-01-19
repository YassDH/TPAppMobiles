package com.gl4.tp5.API

import com.gl4.tp5.Models.weatherForecast.Forecast
import com.gl4.tp5.Models.weatherModels.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
interface WeatherAPI {
    @GET("weather?APPID=4ed071705019536d4cd55e0240ed6e5f")
    fun getWeather(@Query("q")city: String): Call<WeatherResponse>

    @GET("forecast/daily?APPID=17db59488cadcad345211c36304a9266&units=metric")
    fun getForecast(@Query("q") city : String): Call<Forecast>
}