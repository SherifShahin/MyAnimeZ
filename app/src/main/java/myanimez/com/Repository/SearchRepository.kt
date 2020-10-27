package myanimez.com.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import myanimez.com.DataBase.AppDao
import myanimez.com.Model.SearchResult
import myanimez.com.WebService.AnimeApi
import retrofit2.HttpException
import java.io.IOException

class SearchRepository (val dao : AppDao , val api : AnimeApi)
{
    val SearchResult : LiveData<List<SearchResult>> = dao.getSearchResult()

    private val _RequestResult:MutableLiveData<String> = MutableLiveData()

    val RequestResult : LiveData<String> = _RequestResult

    suspend fun getSearchResult(query : String){

        try {
            if(query.isNotEmpty()) {
                val response = api.SearchAnime(query, 1)

                dao.DeleteSearchResult()

                dao.setSearchResult(response.results)
            }
        }catch (e: IOException) {
            _RequestResult.value = e.message
        } catch (e: HttpException) {
            _RequestResult.value = e.message
        }catch (e :Exception) {
            _RequestResult.value = e.message
        }
    }
}