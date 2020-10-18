package myanimez.com.RemoteMediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import myanimez.com.DataBase.AppDao
import myanimez.com.Model.PageNumber
import myanimez.com.Model.TopAnime
import myanimez.com.WebService.TopAnimeApi
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class TopAnimeRemoteMediator(val dao : AppDao, val api : TopAnimeApi , val Subtype:String)
    : RemoteMediator<Int, TopAnime>()
{
    override suspend fun load(loadType: LoadType, state: PagingState<Int, TopAnime>): MediatorResult {

        var loadKey = 1

        if (loadType == LoadType.REFRESH) {
            try {
                dao.clearNumber()
            }
            catch (e:Exception){}

            loadKey = 1
            dao.clearNumber()
            dao.setNumber(PageNumber(1))
        }
        else if(loadType == LoadType.PREPEND){
            return  MediatorResult.Success(
                endOfPaginationReached = true
            )
        }
        else if(loadType == LoadType.APPEND) {

            try{
                if(dao.getPageNumber() == null)
                {
                    loadKey = 1
                    dao.clearNumber()
                    dao.setNumber(PageNumber(1))
                    dao.clearTopAnimes()
                }
                else{
                    loadKey = dao.getPageNumber().number
                }
            } catch (e:Exception){ MediatorResult.Error(e)}
        }

        return try {

                val response = api.GetTopAnime(loadKey,Subtype)

                if(loadType == LoadType.REFRESH)
                {
                    try {
                        dao.clearTopAnimes()
                    }
                    catch (e:Exception){
                        MediatorResult.Error(e)
                    }
                }

                val currentPageNumber = dao.getPageNumber().number
                dao.clearNumber()
                dao.setNumber(PageNumber(currentPageNumber + 1))
                dao.setTopAnime(response.top)

            MediatorResult.Success(
                endOfPaginationReached = response.top.isEmpty()
            )

        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }catch (e:Exception){
            MediatorResult.Error(e)
        }
    }

}