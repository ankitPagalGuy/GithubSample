package com.example.ankit.githubsample.ui.search

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.example.ankit.githubsample.data.GithubApiInterface
import com.example.ankit.githubsample.data.Search
import com.example.ankit.githubsample.data.User
import com.example.ankit.githubsample.data.Userapi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by ankit on 24/03/18.
 */

class SearchPresenter{

    var list = MutableLiveData<List<User>>()

    fun getData(q: String) {
        Userapi.getGitRetrofit().create(GithubApiInterface::class.java)
                .getAllSearchedUsers(q).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
                .subscribe(object : io.reactivex.Observer<Search> {
                    override fun onComplete() {
                    }

                    override fun onError(e: Throwable) {
                        Log.e("SEARCH ERROR: ", e.localizedMessage)
                    }

                    override fun onNext(t: Search) {
                        if (t != null) {
                            Log.e("SEARCH RESPONSE: ", t.toString())
                            list.postValue(t.items)
                        }
                    }

                    override fun onSubscribe(d: Disposable) {

                    }

                })
    }
}