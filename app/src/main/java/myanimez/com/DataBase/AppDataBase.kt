package myanimez.com.DataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import myanimez.com.Model.PageNumber
import myanimez.com.Model.Anime
import myanimez.com.Model.SeasonAnime


@Database(entities = [Anime::class ,SeasonAnime::class ,PageNumber::class],
    version = 5
    ,exportSchema = false)

abstract class AppDataBase : RoomDatabase()
{
    abstract fun getAppDao() : AppDao

    companion object{

        @Volatile
        private var instance : AppDataBase?= null

        private val Lock = Any()

        operator  fun invoke(context: Context) = instance ?: synchronized(Lock)
        {
            instance ?: buildDataBase(context)
        }

        fun buildDataBase(context : Context) =
            Room.databaseBuilder(context,
                AppDataBase::class.java
                ,"MyAnimeZ.db"
                ).fallbackToDestructiveMigration()
                .build()
    }
}