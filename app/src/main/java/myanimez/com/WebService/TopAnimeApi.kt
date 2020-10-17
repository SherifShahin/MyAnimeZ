package myanimez.com.WebService

import myanimez.com.Response.TopAnimeResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface TopAnimeApi
{
    @GET("top/anime/{page}/{subtype}")
    suspend fun GetTopAnime(
        @Path("page") page : Int ,
        @Path("subtype") subtype:String
    ) : TopAnimeResponse

    companion object  {
        private const val url = "https://api.jikan.moe/v3/"
        operator fun invoke() : TopAnimeApi{
            return  Retrofit.Builder()
                .baseUrl("$url")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(TopAnimeApi::class.java)
        }
    }
}