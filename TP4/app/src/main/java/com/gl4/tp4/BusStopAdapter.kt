package com.gl4.tp4
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gl4.tp4.database.entities.Schedule
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.Locale
import kotlin.time.Duration


class BusStopAdapter(private var busStops: List<Schedule>, private val onItemClick: (Schedule) -> Unit) :
    RecyclerView.Adapter<BusStopAdapter.ViewHolder>() {

    fun Long.toTimeDateString(): String {
        val dateTime = java.util.Date(this)
        val format = SimpleDateFormat("HH:mm:ss dd/MM/yyyy", Locale.US)
        return format.format(dateTime)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val stopNameTextView: TextView = itemView.findViewById(R.id.stopNameTextView)
        val arrivalTimeTextView: TextView = itemView.findViewById(R.id.arrivalTimeTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_bus_stop, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentBusStop = busStops[position]

        holder.stopNameTextView.text = currentBusStop.stop_name
        holder.arrivalTimeTextView.text = currentBusStop.arrival_time.toLong().toTimeDateString()

        // Set click listener on the item
        holder.itemView.setOnClickListener {
            onItemClick(currentBusStop)
        }

    }
    override fun getItemCount(): Int {
        return busStops.size
    }
    fun updateList(newList: List<Schedule>) {
        busStops = newList
        notifyDataSetChanged()
    }




}