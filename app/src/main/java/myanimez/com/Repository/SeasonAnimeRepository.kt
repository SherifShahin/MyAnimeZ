package myanimez.com.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import myanimez.com.DataBase.AppDao
import myanimez.com.Model.SeasonAnime
import myanimez.com.WebService.AnimeApi
import retrofit2.HttpException
import java.io.IOException
import java.util.*

class SeasonAnimeRepository(val dao : AppDao , val api : AnimeApi) {

    val SeasonAnimes : LiveData<List<SeasonAnime>> = dao.getSeasonAnime()

    private val _RequestResult:MutableLiveData<String> = MutableLiveData()

    val RequestResult : LiveData<String> = _RequestResult

    suspend fun getSeasonAnime()
    {
        val season = findSeason(Calendar.getInstance().get(Calendar.MONTH)+1)

        val year = Calendar.getInstance().get(Calendar.YEAR)

        try {
            val response = api.GetSeasonAnime(year , season)

            dao.clearSeasonAnimes()
            dao.setSeasonAnime(response.anime)

        }catch (e: IOException) {
            _RequestResult.value = e.message
        } catch (e: HttpException) {
            _RequestResult.value = e.message
        }catch (e :Exception) {
            _RequestResult.value = e.message
        }
    }

    fun findSeason(M: Int) : String {

        when (M) {
            12, 1, 2 -> return "winter"
            3, 4, 5 ->  return "spring"
            6, 7, 8 -> return "summer"
            9, 10, 11 -> return "fall"
            else -> return "winter"
        }
    }
}