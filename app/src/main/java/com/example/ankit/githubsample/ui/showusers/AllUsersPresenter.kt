package com.example.ankit.githubsample.ui.showusers

import android.arch.lifecycle.LiveData
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import android.content.Context
import com.example.ankit.githubsample.data.AllUserapi
import com.example.ankit.githubsample.data.DataManager
import com.example.ankit.githubsample.data.User

/**
 * Created by ankit on 24/03/18.
 */

class AllUsersPresenter(var context: Context){

    private var pagelistConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(10).build();
    var allUserapi = AllUserapi(context)


    fun getFromDb(): LiveData<PagedList<User>> {

        return LivePagedListBuilder(DataManager.getGithubDb(context).getGithubDao().getAllDataFromDb()
                , pagelistConfig)
                .setBoundaryCallback(allUserapi)
                .build()

    }

    fun getLoadingStatus(): LiveData<String> = allUserapi.getLoadStatus()
}