package com.gl4.tp4.views
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.gl4.tp4.BusStopAdapter
import com.gl4.tp4.databinding.FragmentBusDetailsBinding
import com.gl4.tp4.database.BusScheduleApplication
import com.gl4.tp4.viewmodels.BusScheduleViewModel
import com.gl4.tp4.viewmodels.BusScheduleViewModelFactory
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager

class FragmentBusDetails : Fragment() {
    private lateinit var binding: FragmentBusDetailsBinding
    private lateinit var stopName: String
    private lateinit var navController: NavController
    private lateinit var recyclerView: RecyclerView
    private lateinit var busStopAdapter: BusStopAdapter

    private val viewModel: BusScheduleViewModel by viewModels {
        BusScheduleViewModelFactory(
            (requireContext().applicationContext as BusScheduleApplication).database.scheduleDao()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            stopName = it.getString("stopName").toString()
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBusDetailsBinding.inflate(LayoutInflater.from(requireContext()), container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController=findNavController()
        recyclerView = binding.recyclerViewDetails
        recyclerView.layoutManager = LinearLayoutManager(context)


        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        busStopAdapter= BusStopAdapter(emptyList()) { schedule ->
            println(schedule)
        }
        recyclerView.adapter = busStopAdapter
        viewModel.scheduleForStopName(stopName!!).observe(viewLifecycleOwner) {
            busStopAdapter.updateList(it)
        }
    }
}