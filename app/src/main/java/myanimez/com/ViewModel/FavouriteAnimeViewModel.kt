package myanimez.com.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import myanimez.com.Model.FavouriteAnime
import myanimez.com.Repository.FavouriteAnimeRepository

class FavouriteAnimeViewModel(val repository: FavouriteAnimeRepository) : ViewModel() {

    fun getFavouriteAnime() : LiveData<List<FavouriteAnime>> = repository.FavouriteAnime

}
