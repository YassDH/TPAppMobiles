package com.gl4.tp5.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gl4.tp5.Models.weatherForecast.Forecast
import com.gl4.tp5.R

class WeatherAdapter(private val forecast : Forecast?) : RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {
    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val pressure : TextView
        val temperature : TextView
        val humidity : TextView
        init {
            pressure = itemView.findViewById(R.id.pressure)
            temperature = itemView.findViewById(R.id.temperature)
            humidity = itemView.findViewById(R.id.humidity)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.weather_item,parent,false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.pressure.text = "Pressure ${forecast!!.list[position].pressure.toString()}"
        holder.temperature.text = "Temperature ${forecast!!.list[position].temp.day.toString()}"
        holder.humidity.text = "Humidity ${forecast!!.list[position].humidity.toString()}"
    }
    override fun getItemCount(): Int {
        if(forecast != null) return forecast.list.size
        return 0
    }
}