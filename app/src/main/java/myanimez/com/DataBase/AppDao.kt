package myanimez.com.DataBase

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.*
import androidx.room.Dao
import myanimez.com.Model.PageNumber
import myanimez.com.Model.Anime
import myanimez.com.Model.FavouriteAnime
import myanimez.com.Model.SeasonAnime

@Dao
interface AppDao
{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setAnime(anime : List<Anime>)

    @Query("SELECT * FROM topAnime")
    fun getAnime() : PagingSource<Int,Anime>

    @Query("DELETE FROM topAnime")
    suspend fun clearAnimes()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setSeasonAnime(seasonAnime : List<SeasonAnime>)

    @Query("SELECT * FROM season_anime")
    fun getSeasonAnime() : LiveData<List<SeasonAnime>>

    @Query("DELETE FROM season_anime")
    suspend fun clearSeasonAnimes()

    @Query("SELECT * FROM page")
    suspend fun getPageNumber() : PageNumber

    @Query("DELETE FROM page")
    suspend fun clearNumber()

    @Insert
    suspend fun setNumber(page: PageNumber)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setFavouriteAnime(favouriteAnime : FavouriteAnime)

    @Query("SELECT * FROM favourite")
    fun getFavouriteAnime() : LiveData<List<FavouriteAnime>>

    @Query("SELECT EXISTS(SELECT * from favourite WHERE mal_id =:id)")
    fun checkExistInFavourite(id : Int) : LiveData<Int>

    @Query("DELETE FROM FAVOURITE WHERE mal_id = :id")
    suspend fun DeleteFromFavourite(id : Int)
}