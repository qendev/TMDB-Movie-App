package com.example.movieapp.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.movieapp.model.Movie

@Database(entities = arrayOf(Movie::class), version = 1, exportSchema = false)
abstract class MovieDataBase:RoomDatabase() {
    abstract fun moviedao(): MovieDao

    companion object {
        @Volatile
        private var instance: MovieDataBase? = null
        private const val DATABASE_NAME = "movie_database"

        fun getdatabase(context: Context): MovieDataBase {
            return instance ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MovieDataBase::class.java,
                    DATABASE_NAME
                ).build()
                Companion.instance = instance
                instance
            }
        }
    }


}