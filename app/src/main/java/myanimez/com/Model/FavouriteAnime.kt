package myanimez.com.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourite")
data class FavouriteAnime(
    @PrimaryKey
    @ColumnInfo(name = "mal_id")
    var mal_id : Int?,
    @ColumnInfo(name = "image_url")
    var image_url : String?,
    @ColumnInfo(name = "title")
    var title : String?,
    @ColumnInfo(name = "score")
    var score : Float?
)