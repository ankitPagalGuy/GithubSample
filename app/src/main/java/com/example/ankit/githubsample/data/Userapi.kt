package com.example.ankit.githubsample.data

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by ankit on 24/03/18.
 */

class  Userapi {

    companion object {
        var retrofit: Retrofit? = null

        fun getGitRetrofit(): Retrofit {
            var gson= GsonBuilder().setLenient().create()
            retrofit = Retrofit.Builder()
                    .baseUrl("https://api.github.com/")
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
            return retrofit!!
        }
        fun getGitRetrofitLogin(): Retrofit {
            retrofit = Retrofit.Builder()
                    .baseUrl("https://github.com/")
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            return retrofit!!
        }

    }
}