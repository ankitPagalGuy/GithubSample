package com.example.ankit.githubsample.data

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

/**
 * Created by ankit on 24/03/18.
 */

class SharedPrefsHelper(context: Context) {

    internal var mSharedPreferences: SharedPreferences
    val MY_PREFS = "MY_PREFS"



    fun getLoggedInMode(): Boolean = mSharedPreferences.getBoolean("IS_LOGGED_IN", false)


    fun setLoggedInMode(loggedIn: Boolean) {
        mSharedPreferences.edit().putBoolean("IS_LOGGED_IN", loggedIn).apply()
    }

    init {
        mSharedPreferences = context.getSharedPreferences(MY_PREFS, MODE_PRIVATE)
    }

    fun clear() {
        mSharedPreferences.edit().clear().apply()
    }





}