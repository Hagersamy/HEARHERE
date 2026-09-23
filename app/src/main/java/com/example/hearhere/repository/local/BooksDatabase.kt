package com.example.hearhere.repository.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.hearhere.models.dbModels.DBBookModel

// to tell the compiler this is the databas
@Database(entities = [DBBookModel::class], version = 1)
abstract class BooksDatabase : RoomDatabase() {
    abstract fun getBooksDao() : BooksDao

    companion object{
        //any thing here is  static we can access it by the name
        @Volatile // any change here is visible for other
        private var instance: BooksDatabase? = null //
        // to make one process use this function and the other wait until finish
        private val LOCK = Any() // use to synchroniz datebase creation process
        // this to make one process initialized the database
        operator  fun invoke(context: Context) = instance?:
        synchronized(LOCK){
            // if instance still null create database function
            instance ?:
            createDatabase(context).also{
                instance = it

            }
        }
        // responspale for create database
        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                BooksDatabase::class.java,
                "books_db"
            ).build()
    }
}