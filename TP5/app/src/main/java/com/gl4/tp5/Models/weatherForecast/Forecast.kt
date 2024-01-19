package com.gl4.tp5.Models.weatherForecast

data class Forecast(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<Infos>,
    val message: Double
)