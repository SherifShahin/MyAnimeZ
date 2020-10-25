package myanimez.com.Response

import myanimez.com.Model.AnimeAired
import myanimez.com.Model.AnimeGenre

data class AnimeDetailsResponse(
    val mal_id : Int? ,
    val image_url : String?,
    val title : String?,
    val type: String?,
    val episodes : Int?,
    val aired : AnimeAired?,
    val score : Float? ,
    val scored_by : Int? ,
    val synopsis : String?,
    val premiered : String? ,
    val status : String ?,
    val source : String ?,
    val genres : List<AnimeGenre>?,
    val duration : String?
    )