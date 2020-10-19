package myanimez.com.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "topAnime")
data class Anime (

    @ColumnInfo(name = "mal_id")
    val mal_id :Int ,

    @PrimaryKey
    @ColumnInfo(name = "title")
    val title : String ,

    @ColumnInfo(name = "image_url")
    val image_url : String ,

    @ColumnInfo(name = "score")
    val score :Float ,

    @ColumnInfo(name = "type")
    val type : String
)
