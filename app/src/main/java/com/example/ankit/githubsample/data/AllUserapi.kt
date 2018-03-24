package com.example.ankit.githubsample.data

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.paging.PagedList
import android.content.Context
import android.util.Log
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by ankit on 24/03/18.
 */

class AllUserapi(var context: Context) : PagedList.BoundaryCallback<User>() {

    var loading = MutableLiveData<String>()

    override fun onItemAtEndLoaded(itemAtEnd: User) {

        getMoreUsers(itemAtEnd)
    }

    override fun onItemAtFrontLoaded(itemAtFront: User) {

    }

    override fun onZeroItemsLoaded() {
        getInitialUsers()
    }

    fun getInitialUsers() {
       Userapi.getGitRetrofit()
                .create(GithubApiInterface::class.java).getAllUsers(0, 10)
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .subscribe(object : Observer<List<User>> {
                    override fun onComplete() {
                        loading.postValue("complete")
                    }

                    override fun onError(e: Throwable) {
                        loading.postValue("error")
                        Log.e("Error", e.localizedMessage)
                    }

                    override fun onNext(t: List<User>) {

                        if (t != null) {
                            loading.postValue("loading")

                            Log.e("success", t.toString())
                            for (i in 0..t?.size!! - 1) {
                                DataManager.getGithubDb(context).getGithubDao().insertUserIndb(t!!.get(i))
                            }
                        }
                    }

                    override fun onSubscribe(d: Disposable) {
                    }
                })

    }


    fun getMoreUsers(item: User) {
        Userapi.getGitRetrofit()
                .create(GithubApiInterface::class.java).getAllUsers(item.id, 10)
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .subscribe(object : Observer<List<User>> {
                    override fun onComplete() {
                        loading.postValue("complete")

                        Log.e("onComplete", "Completed")
                    }

                    override fun onError(e: Throwable) {
                        loading.postValue("error")

                        Log.e("Error", e.localizedMessage)
                    }

                    override fun onNext(t: List<User>) {
                        if (t != null) {
                            loading.postValue("loading")

                            Log.e("success", t.toString())

                            for (i in 0..t?.size!! - 1) {
                                DataManager.getGithubDb(context).getGithubDao().insertUserIndb(t!!.get(i))
                            }
                        }
                    }

                    override fun onSubscribe(d: Disposable) {
                    }
                })

    }

    fun getLoadStatus(): LiveData<String> = loading
}