package com.gl4.tp5.Models.weatherForecast

data class Infos (
    val clouds: Int,
    val deg: Int,
    val dt: Int,
    val feels_like: FeelsLike,
    val gust: Double,
    val humidity: Int,
    val pop: Float,
    val pressure: Int,
    val speed: Double,
    val sunrise: Int,
    val sunset: Int,
    val temp: Temp,
    val weather: List<Weather>
)