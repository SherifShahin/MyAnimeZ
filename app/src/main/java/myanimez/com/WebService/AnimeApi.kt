package myanimez.com.WebService

import myanimez.com.Response.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface AnimeApi
{
    @GET("top/anime/{page}/{subtype}")
    suspend fun GetTopAnime(
        @Path("page") page : Int ,
        @Path("subtype") subtype:String
    ) : TopAnimeResponse

    @GET("season/{year}/{season}")
    suspend fun GetSeasonAnime(
        @Path("year") year : Int ,
        @Path("season") subtype:String
    ) : SeasonAnimeResponse

    @GET("anime/{id}")
    suspend fun GetAnimeDetails(
        @Path("id") id : Int
    ) : AnimeDetailsResponse


    @GET("anime/{id}/characters_staff")
    suspend fun GetAnimeCharacters(
        @Path("id") id : Int
    ) : AnimeCharactersResponse

    @GET("anime/{id}/recommendations")
    suspend fun GetAnimeRecommendations(
        @Path("id") id : Int
    ) : AnimeRecommendationsResponse

    @GET("search/anime")
    suspend fun SearchAnime(
        @Query("q") search : String ,
        @Query("page") page : Int
    ) : SearchResponse

    @GET("schedule")
    suspend fun GetSchedule():ScheduleResponse

    companion object  {
        private const val url = "https://api.jikan.moe/v3/"
        operator fun invoke() : AnimeApi{
            return  Retrofit.Builder()
                .baseUrl("$url")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(AnimeApi::class.java)
        }
    }
}