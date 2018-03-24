package com.example.ankit.githubsample.data

import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by ankit on 24/03/18.
 */

interface GithubApiInterface {

    @GET("users")
    fun getAllUsers(@Query("since") id: Long,
                    @Query("per_page") pagesize: Long): Observable<List<User>>

    @Headers("Accept:application/json")
    @POST("login/oauth/access_token")
    @FormUrlEncoded
    fun getAccessToken(@Field("client_id") client_id: String,
                       @Field("client_secret") secret: String,
                       @Field("code") code: String): Call<AccessToken>

    @GET("search/users")
    fun getAllSearchedUsers(@Query("q") key: String): Observable<Search>

}