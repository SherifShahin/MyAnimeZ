package myanimez.com.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import myanimez.com.Model.AnimeCharacter
import myanimez.com.Model.AnimeDetails
import myanimez.com.Model.AnimeRecommendation
import myanimez.com.Repository.AnimeDetailsRepository

class AnimeDetailsViewModel(val repository: AnimeDetailsRepository) :ViewModel(){

    fun getAnimeDetails() : LiveData<AnimeDetails> = repository.AnimedDeatils

    fun getAnimeCharacters() : LiveData<List<AnimeCharacter>> = repository.AnimeCharacters

    fun getAnimeRecommendations() : LiveData<List<AnimeRecommendation>> = repository.AnimeRecommendations

    fun getRequestResult() : LiveData<String> = repository.RequestResult

    fun RequestAnimeDetails(id:Int?) {
         viewModelScope.launch {
             id?.let {
                 repository.getAnimeDetails(id)
             }
         }
    }
}