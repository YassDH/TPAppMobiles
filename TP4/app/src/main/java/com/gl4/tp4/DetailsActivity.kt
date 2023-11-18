package com.gl4.tp4
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gl4.tp4.database.BusScheduleApplication
import com.gl4.tp4.databinding.ActivityDetailsBinding
import com.gl4.tp4.databinding.ActivityMainBinding
import com.gl4.tp4.viewmodels.BusScheduleViewModel
import com.gl4.tp4.viewmodels.BusScheduleViewModelFactory
class DetailsActivity : ComponentActivity() {
    private lateinit var binding: ActivityDetailsBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var busStopAdapter: BusStopAdapter
    private val viewModel : BusScheduleViewModel by viewModels() {
        BusScheduleViewModelFactory(
            (application as
                    BusScheduleApplication).database.scheduleDao()
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val stopName = intent.getStringExtra("stopName")

        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        recyclerView = binding.recyclerViewDetails

        recyclerView.layoutManager = LinearLayoutManager(this)

        busStopAdapter = BusStopAdapter(emptyList()) { schedule ->

            println()
        }
        recyclerView.adapter = busStopAdapter
        viewModel.scheduleForStopName(stopName!!).observe(this) {
            busStopAdapter.updateList(it)
        }

    }
}