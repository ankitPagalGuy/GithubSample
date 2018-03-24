package com.example.ankit.githubsample.ui.showusers

import android.arch.lifecycle.Observer
import android.arch.paging.PagedList
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.example.ankit.githubsample.R
import com.example.ankit.githubsample.data.SharedPrefsHelper
import com.example.ankit.githubsample.data.User
import com.example.ankit.githubsample.ui.login.LoginActivity
import com.example.ankit.githubsample.ui.search.SearchActivity
import kotlinx.android.synthetic.main.activity_show_users.*

/**
 * Created by ankit on 24/03/18.
 */

class  AllUsersActivity: AppCompatActivity() {

    var presenter = AllUsersPresenter(this)
    var adapter = AllUsersAdapter(DiffCallBack.provideDiffCallBack(), this)
    lateinit var mSharedPrefsHelper: SharedPrefsHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_users)


        mSharedPrefsHelper = SharedPrefsHelper(this)
        recyler_view_user.adapter = adapter
        recyler_view_user.layoutManager = LinearLayoutManager(this)
        presenter.getFromDb().observe(this, Observer<PagedList<User>> { t -> adapter.submitList(t) })

        presenter.getLoadingStatus().observe(this, object : Observer<String> {
            override fun onChanged(t: String?) {
                if (t == "loading")
                    progress.visibility = View.VISIBLE
                else if (t == "error") {
                    Toast.makeText(this@AllUsersActivity, "ERROR OCCURED", Toast.LENGTH_SHORT).show()
                    progress.visibility = View.GONE
                } else
                    progress.visibility = View.GONE
            }
        })

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        val id = item!!.itemId
        if (id == R.id.search) {
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }
        else if (id == R.id.logout){
            mSharedPrefsHelper.clear()
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        return super.onOptionsItemSelected(item)
    }

}