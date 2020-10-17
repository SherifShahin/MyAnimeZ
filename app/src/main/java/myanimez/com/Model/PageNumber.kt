package myanimez.com.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "page")
data class PageNumber (
    @PrimaryKey
    @ColumnInfo(name = "number")
    val number: Int
)
