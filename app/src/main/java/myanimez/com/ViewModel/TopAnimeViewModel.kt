package myanimez.com.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import kotlinx.coroutines.launch
import myanimez.com.Model.Anime
import myanimez.com.Repository.TopAnimeRepository

class TopAnimeViewModel(val repository: TopAnimeRepository) : ViewModel() {

    fun getTopAnimes(substype:String): LiveData<PagingData<Anime>> = repository.getTopAnimes(substype).asLiveData()
    fun clearData() {
        viewModelScope.launch {
            repository.clearData()
        }
    }

    fun getNavigate() : LiveData<Int> = repository.navigate
    fun resetNavigation() {
        repository.resetNavigation()
    }
}
