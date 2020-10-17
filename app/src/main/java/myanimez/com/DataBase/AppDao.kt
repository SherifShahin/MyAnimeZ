package myanimez.com.DataBase

import androidx.paging.PagingSource
import androidx.room.*
import androidx.room.Dao
import myanimez.com.Model.PageNumber
import myanimez.com.Model.TopAnime

@Dao
interface AppDao
{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setTopAnime(anime : List<TopAnime>)

    @Query("SELECT * FROM topAnime")
    fun getTopAnime() : PagingSource<Int,TopAnime>

    @Query("DELETE FROM topAnime")
    suspend fun clearTopAnimes()

    @Query("SELECT * FROM page")
    suspend fun getPageNumber() : PageNumber

    @Query("DELETE FROM page")
    suspend fun clearNumber()

    @Insert
    suspend fun setNumber(page: PageNumber)

}