package com.example.ankit.githubsample.ui.login

import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.util.Log
import com.example.ankit.githubsample.Secret
import com.example.ankit.githubsample.data.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by ankit on 24/03/18.
 */

class LoginPresenter(var context: Context){

     var login = MutableLiveData<Boolean>()
     var mSharedPrefsHelper: SharedPrefsHelper = SharedPrefsHelper(context)


    fun startLogin(code: String) {
        login.postValue(false)
        Userapi.getGitRetrofitLogin().create(GithubApiInterface::class.java)
                .getAccessToken(Secret.CLIENT_ID, Secret.CLIENT_SECRET, code)
                .enqueue(object : Callback<AccessToken> {
                    override fun onFailure(call: Call<AccessToken>?, t: Throwable?) {
                        Log.e("LOGIN ERROR: ", t?.localizedMessage)
                    }

                    override fun onResponse(call: Call<AccessToken>?, response: Response<AccessToken>?) {
                        if (response != null) {
                            Log.e("SUCCESS: ", response.toString())
                            login.postValue(true)
                            mSharedPrefsHelper.setLoggedInMode(true)
                        }
                    }
                })
    }
}