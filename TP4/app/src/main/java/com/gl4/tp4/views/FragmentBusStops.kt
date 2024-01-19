package com.gl4.tp4.views
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.gl4.tp4.databinding.FragmentBusStopsBinding
import androidx.navigation.NavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gl4.tp4.BusStopAdapter
import com.gl4.tp4.database.BusScheduleApplication
import com.gl4.tp4.viewmodels.BusScheduleViewModel
import com.gl4.tp4.viewmodels.BusScheduleViewModelFactory
import androidx.navigation.fragment.findNavController


class FragmentBusStops : Fragment() {
    private lateinit var binding: FragmentBusStopsBinding
    private lateinit var busStopAdapter: BusStopAdapter
    private lateinit var navController: NavController
    private lateinit var recyclerView: RecyclerView
    private val viewModel: BusScheduleViewModel by viewModels {
        BusScheduleViewModelFactory(
            (requireContext().applicationContext as BusScheduleApplication).database.scheduleDao()
        )
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBusStopsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController=findNavController()

        recyclerView= binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        busStopAdapter= BusStopAdapter(emptyList()) { schedule ->
            val action = FragmentBusStopsDirections.actionFragmentBusStopsToFragmentBusDetails(
                schedule.stop_name
            )
            findNavController().navigate(action)
        }
        recyclerView.adapter = busStopAdapter

        viewModel.fullSchedule().observe(viewLifecycleOwner) {
            busStopAdapter.updateList(it)
        }

    }
}