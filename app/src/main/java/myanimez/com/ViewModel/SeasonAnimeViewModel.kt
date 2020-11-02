package myanimez.com.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import myanimez.com.Model.SeasonAnime
import myanimez.com.Repository.SeasonAnimeRepository

class SeasonAnimeViewModel(val repository: SeasonAnimeRepository) : ViewModel()
{
    fun getSeasonAnime():LiveData<List<SeasonAnime>> = repository.SeasonAnimes

    fun getRequestResult() : LiveData<String> = repository.RequestResult

    fun RequestNewData() {
        viewModelScope.launch {
            repository.getSeasonAnime()
        }
    }
}