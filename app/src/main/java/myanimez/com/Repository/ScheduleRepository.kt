package myanimez.com.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import myanimez.com.Model.Anime
import myanimez.com.Response.ScheduleResponse
import myanimez.com.WebService.AnimeApi
import retrofit2.HttpException
import java.io.IOException

class ScheduleRepository(val api : AnimeApi)
{
    private val _Schedule : MutableLiveData<ScheduleResponse> = MutableLiveData()

    private val _RequestResult:MutableLiveData<String> = MutableLiveData()

    val RequestResult : LiveData<String> = _RequestResult

    val Schedule : LiveData<ScheduleResponse> = _Schedule

    suspend fun GetSchedule(){
        try{

            val response = api.GetSchedule()

            _Schedule.value = response

        }catch (e: IOException) {
            _RequestResult.value = e.message
        } catch (e: HttpException) {
            _RequestResult.value = e.message
        }catch (e :Exception) {
            _RequestResult.value = e.message
        }
    }
}