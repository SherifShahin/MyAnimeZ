package myanimez.com.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import myanimez.com.DataBase.AppDao
import myanimez.com.Model.AnimeCharacter
import myanimez.com.Model.AnimeDetails
import myanimez.com.Model.AnimeRecommendation
import myanimez.com.Model.FavouriteAnime
import myanimez.com.Response.AnimeCharactersResponse
import myanimez.com.Response.AnimeDetailsResponse
import myanimez.com.Response.AnimeRecommendationsResponse
import myanimez.com.WebService.AnimeApi
import retrofit2.HttpException
import java.io.IOException

class AnimeDetailsRepository(val dao : AppDao ,val api : AnimeApi)
{
    private val _AnimeDetails : MutableLiveData<AnimeDetails> = MutableLiveData()
    val AnimedDeatils : LiveData<AnimeDetails> = _AnimeDetails

    private val _AnimeCharacters : MutableLiveData<List<AnimeCharacter>> = MutableLiveData()
    val AnimeCharacters : LiveData<List<AnimeCharacter>> = _AnimeCharacters

    private val _AnimeRecommendations : MutableLiveData<List<AnimeRecommendation>> = MutableLiveData()
    val AnimeRecommendations : LiveData<List<AnimeRecommendation>> = _AnimeRecommendations

    private val _RequestResult: MutableLiveData<String> = MutableLiveData()

    val RequestResult : LiveData<String> = _RequestResult



    suspend fun getAnimeDetails(id : Int)
    {
        try {
            val response = api.GetAnimeDetails(id)

            val charactersResponse = api.GetAnimeCharacters(id)

            val recommendationResponse = api.GetAnimeRecommendations(id)

            saveAnimeDetails(response)
            saveAnimeCharacters(charactersResponse)
            saveAnimeRecommendations(recommendationResponse)

        }catch (e: IOException) {
            _RequestResult.value = e.message
        } catch (e: HttpException) {
            _RequestResult.value = e.message
        }catch (e :Exception) {
            _RequestResult.value = e.message
        }
    }

    private fun saveAnimeRecommendations(recommendationResponse: AnimeRecommendationsResponse) {
            _AnimeRecommendations.value = recommendationResponse.recommendations
    }

    private fun saveAnimeCharacters(charactersResponse: AnimeCharactersResponse) {
            _AnimeCharacters.value = charactersResponse.characters
    }

    private  fun  saveAnimeDetails(response: AnimeDetailsResponse) {

        val anime = AnimeDetails(
                mal_id = response.mal_id!!,
                image_url = response.image_url!! ,
                source = response.source ?: "null",
                scored_by = response.scored_by ?: 0,
                score = response.score ?: 0.0,
                premiered = response.premiered ?: "null",
                status = response.status ?: "null",
                title = response.title!!,
                aired = response.aired?.string ?: "null",
                synopsis = response.synopsis ?: "null",
                episodes = response.episodes ?: 0,
                type = response.type ?: "null",
                duration = response.duration ?: "null",
                genres = response.genres
            )

        _AnimeDetails.value = anime
    }

    suspend fun addToFavourite(anime: AnimeDetails) {

        val favourite = FavouriteAnime(
            mal_id = anime.mal_id,
            title = anime.title,
            image_url = anime.image_url ,
            score = anime.score?.toFloat()
        )

        dao.setFavouriteAnime(favourite)
    }

    fun InFavourite(id : Int):LiveData<Int> =  dao.checkExistInFavourite(id)

    suspend fun deleteFromFavourite(malId: Int?) {
        dao.DeleteFromFavourite(malId!!)
    }


}