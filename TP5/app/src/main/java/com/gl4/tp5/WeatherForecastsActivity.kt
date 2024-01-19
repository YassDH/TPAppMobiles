package com.gl4.tp5

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.gl4.tp5.ViewModels.ForecastViewModel
import com.gl4.tp5.Adapters.WeatherAdapter
import com.gl4.tp5.databinding.WeatherForecastBinding

class WeatherForecastsActivity : AppCompatActivity()  {
    private lateinit var binding : WeatherForecastBinding
    var forecastViewModel : ForecastViewModel = ForecastViewModel(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = WeatherForecastBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val city= intent.getStringExtra("city")
        if(city != null){
            forecastViewModel.getForecast(city)
        }
        forecastViewModel.forecast.observe(this) {
            if (it != null){
                binding.forecastsRecycler.adapter = WeatherAdapter(forecastViewModel.forecast.value)
                binding.cityName.text = forecastViewModel.forecast.value!!.city.name
            }
        }
        binding.forecastsRecycler.apply {
            layoutManager = LinearLayoutManager(this@WeatherForecastsActivity)
            adapter = WeatherAdapter(forecastViewModel.forecast.value)
        }
    }
}