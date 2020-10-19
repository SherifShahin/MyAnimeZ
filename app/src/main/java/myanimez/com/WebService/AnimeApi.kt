package myanimez.com.WebService

import myanimez.com.Response.SeasonAnimeResponse
import myanimez.com.Response.TopAnimeResponse
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