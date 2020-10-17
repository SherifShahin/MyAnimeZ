package myanimez.com.Repository

import android.app.Application
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import myanimez.com.DataBase.AppDao
import myanimez.com.Model.TopAnime
import myanimez.com.RemoteMediator.TopAnimeRemoteMediator
import org.koin.android.ext.android.get
import org.koin.core.parameter.parametersOf

class TopAnimeRepository(val dao : AppDao , val application: Application)
{
    fun getTopAnimes(SubType : String) : Flow<PagingData<TopAnime>> {

        return Pager(
            config = PagingConfig(pageSize = 50
                 , maxSize = 200
                ,  enablePlaceholders = false
            ),
            remoteMediator = application.get<TopAnimeRemoteMediator>{ parametersOf(SubType)}
        ){
            dao.getTopAnime()
        }.flow
    }
}