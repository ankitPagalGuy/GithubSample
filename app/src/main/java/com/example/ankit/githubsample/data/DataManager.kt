package com.example.ankit.githubsample.data

import android.arch.paging.DataSource
import android.arch.persistence.room.*
import android.content.Context

/**
 * Created by ankit on 24/03/18.
 */

@Database(entities = arrayOf(User::class),version = 1)
abstract class DataManager: RoomDatabase(){


    companion object {
        var dataManager:DataManager?=null
        fun getGithubDb(context: Context):DataManager{
            if (dataManager==null){
               dataManager = Room.databaseBuilder(context.applicationContext, DataManager::class.java, "github")
                        .fallbackToDestructiveMigration()
                        .build()
            }
            return dataManager!!
        }
    }


    abstract fun getGithubDao():GithubDao
}




@Dao
interface GithubDao {

    @Query("SELECT * FROM User LIMIT 200")
    fun getAllDataFromDb(): DataSource.Factory<Int, User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserIndb(user: User)
}