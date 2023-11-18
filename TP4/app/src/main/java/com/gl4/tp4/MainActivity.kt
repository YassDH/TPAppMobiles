package com.gl4.tp4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gl4.tp4.database.BusScheduleApplication
import com.gl4.tp4.databinding.ActivityMainBinding
import com.gl4.tp4.viewmodels.BusScheduleViewModel
import com.gl4.tp4.viewmodels.BusScheduleViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var binding: ActivityMainBinding
    private lateinit  var busStopAdapter: BusStopAdapter
    private val viewModel : BusScheduleViewModel by viewModels() {
        BusScheduleViewModelFactory( (application as BusScheduleApplication).database.scheduleDao())
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)

        busStopAdapter= BusStopAdapter(emptyList()) { schedule ->
            val intent = Intent(this@MainActivity, DetailsActivity::class.java)
            intent.putExtra("stopName", schedule.stop_name)
            startActivity(intent)
        }

        recyclerView.adapter = busStopAdapter

        viewModel.fullSchedule().observe(this) {
            busStopAdapter.updateList(it)
        }

    }
}