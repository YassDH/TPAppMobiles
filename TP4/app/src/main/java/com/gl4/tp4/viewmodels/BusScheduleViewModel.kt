package com.gl4.tp4.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.gl4.tp4.database.entities.Schedule
import com.gl4.tp4.database.entities.ScheduleDao

class BusScheduleViewModel(private val scheduleDao: ScheduleDao): ViewModel() {
    fun fullSchedule(): LiveData<List<Schedule>> = scheduleDao.getAll()
    fun scheduleForStopName(name: String): LiveData<List<Schedule>> = scheduleDao.getByStopName(name)
}
