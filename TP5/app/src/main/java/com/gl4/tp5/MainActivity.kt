package com.gl4.tp5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.bumptech.glide.Glide
import com.gl4.tp5.databinding.ActivityMainBinding
import com.gl4.tp5.Models.weatherModels.WeatherResponse
import com.gl4.tp5.ViewModels.WeatherViewModel
import com.google.android.material.R

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    var weatherViewModel : WeatherViewModel = WeatherViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        weatherViewModel.weather.observe(this) {
            if (it != null)
                setWeather(it)
        }
        val cities = listOf<String>("Tunis", "Brasilia", "Sydney","Cairo","Moscow","London","Tokyo")
        val citiesAdapter = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, cities)
        val spinner = binding.citiesSpinner
        spinner.adapter = citiesAdapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                weatherViewModel.changeCity(cities[p2])
                if(weatherViewModel.weather.value != null){
                    setWeather(weatherViewModel.weather.value!!)
                }

            }
            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }
        binding.forecastButton.setOnClickListener{
            val intent = Intent(this, WeatherForecastsActivity::class.java)
            intent.putExtra("city", spinner.selectedItem.toString())
            startActivity(intent)
        }
    }
    fun setWeather(weather : WeatherResponse){
        Glide.with(this)
            .load("https://openweathermap.org/img/wn/${weather.weather[0].icon}@2x.png")
            .into(binding.weatherIcon)
        binding.cityTextView.text = weather.name
        val temp = weather.main.temp/10
        val formattedTemp = String.format("%.2f", temp)
        binding.temperatureTextView.text = "${formattedTemp}°C"
        binding.weatherTextView.text = weather.weather[0].description
        binding.humidityTextView.text = "Humidity : ${weather.main.humidity}"
        binding.pressureTextView.text = "Pressure : ${weather.main.pressure}"
    }
}