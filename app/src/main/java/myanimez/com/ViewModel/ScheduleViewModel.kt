package myanimez.com.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import myanimez.com.Repository.ScheduleRepository
import myanimez.com.Response.ScheduleResponse

class ScheduleViewModel(val repository: ScheduleRepository) : ViewModel(){

    private val Schedule : LiveData<ScheduleResponse> = repository.Schedule

    private val RequestResult : LiveData<String> = repository.RequestResult

    fun GetRequestResult() : LiveData<String> = repository.RequestResult

    fun GetSchedule() : LiveData<ScheduleResponse> = Schedule

    fun RequestData(){
        viewModelScope.launch {
            repository.GetSchedule()
        }
    }
}