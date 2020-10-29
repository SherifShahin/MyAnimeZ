package myanimez.com.Repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import myanimez.com.DataBase.AppDao
import myanimez.com.Model.Anime
import myanimez.com.RemoteMediator.TopAnimeRemoteMediator
import org.koin.android.ext.android.get
import org.koin.core.parameter.parametersOf

class TopRepository(val dao : AppDao, val application: Application)
{
    private val _navigate : MutableLiveData<Int> = MutableLiveData()
    val navigate : LiveData<Int>  = _navigate

    fun getTopAnimes(Type: String ,SubType : String) : Flow<PagingData<Anime>> {

        return Pager(
            config = PagingConfig(pageSize = 50
                 , maxSize = 200
                ,  enablePlaceholders = false
            ),
            remoteMediator = application.get<TopAnimeRemoteMediator>{ parametersOf(Type,SubType)}
        ){
            dao.getAnime()
        }.flow
    }

    suspend fun clearData() {
        dao.clearAnimes()
    }

    fun setNavigate(value : Int)
    {
        _navigate.value = value
    }

    fun resetNavigation() {
        _navigate.value = 0
    }

}