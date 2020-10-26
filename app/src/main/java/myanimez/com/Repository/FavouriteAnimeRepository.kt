package myanimez.com.Repository

import androidx.lifecycle.LiveData
import myanimez.com.DataBase.AppDao
import myanimez.com.Model.FavouriteAnime

class FavouriteAnimeRepository(dao : AppDao){

    val FavouriteAnime : LiveData<List<FavouriteAnime>> = dao.getFavouriteAnime()

}