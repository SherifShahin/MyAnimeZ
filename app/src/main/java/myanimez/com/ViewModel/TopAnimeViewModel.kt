package myanimez.com.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.paging.PagingData
import myanimez.com.Model.TopAnime
import myanimez.com.Repository.TopAnimeRepository

class TopAnimeViewModel(val repository: TopAnimeRepository) : ViewModel() {

    fun getTopAnimes(substype:String): LiveData<PagingData<TopAnime>> = repository.getTopAnimes(substype).asLiveData()

}
