package com.example.ankit.githubsample.ui.login

import android.arch.lifecycle.Observer
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.example.ankit.githubsample.R
import com.example.ankit.githubsample.Secret
import com.example.ankit.githubsample.data.SharedPrefsHelper
import com.example.ankit.githubsample.ui.showusers.AllUsersActivity
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by ankit on 24/03/18.
 */

class LoginActivity:  AppCompatActivity() {

    lateinit var mSharedPrefsHelper: SharedPrefsHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         mSharedPrefsHelper = SharedPrefsHelper(this)

        if (mSharedPrefsHelper.getLoggedInMode()) {
            val intent = Intent(this@LoginActivity, AllUsersActivity::class.java)
            startActivity(intent)
        }


        login_github.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://github.com/login/oauth/authorize?client_id=${Secret.CLIENT_ID}"))
            startActivity(intent)

        }

    }


    override fun onResume() {
        super.onResume()
        val uri = intent.data
        if (uri != null) {
            login_progress.visibility = View.VISIBLE
            val code = uri.getQueryParameter("code")
            val model = LoginPresenter(this)
            model.startLogin(code)
            model.login.observe(this, Observer<Boolean> { t ->
                if (t == true) {
                    login_progress.visibility = View.INVISIBLE
                    val intent = Intent(this@LoginActivity, AllUsersActivity::class.java)
                    startActivity(intent)
                }
            })

        }

    }
}