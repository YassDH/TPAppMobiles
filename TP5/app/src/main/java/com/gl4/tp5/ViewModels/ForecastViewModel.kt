package com.gl4.tp5.ViewModels
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gl4.tp5.Models.weatherForecast.Forecast
import com.gl4.tp5.API.RetrofitHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ForecastViewModel(private val context : Context) {
    private val forecastResponse = MutableLiveData<Forecast>()
    var forecast : LiveData<Forecast> = forecastResponse
    fun getForecast(city : String){
        RetrofitHelper.retrofitService.getForecast(city).enqueue(
            object : Callback<Forecast> {
                override fun onResponse(
                    call: Call<Forecast>,
                    response: Response<Forecast>
                ) {
                    if(response.isSuccessful){
                        forecastResponse.value = response.body()
                        forecast = forecastResponse
                    }
                }

                override fun onFailure(call: Call<Forecast>, t: Throwable) {
                    println("Weather update failed : ${t.message}")
                }
            }
        )
    }

}