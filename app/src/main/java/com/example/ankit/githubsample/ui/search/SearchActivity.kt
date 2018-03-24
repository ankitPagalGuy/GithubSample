package com.example.ankit.githubsample.ui.search

import android.arch.lifecycle.Observer
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import com.example.ankit.githubsample.R
import com.example.ankit.githubsample.data.User
import kotlinx.android.synthetic.main.activity_search.*

/**
 * Created by ankit on 24/03/18.
 */

class SearchActivity: AppCompatActivity(){

    val presenter = SearchPresenter();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        search_list.layoutManager = LinearLayoutManager(this)


        search_edit.setOnEditorActionListener { p0, p1, p2 ->
            if (p1 == EditorInfo.IME_ACTION_SEARCH) {
                if (search_edit.text.toString() != "") {
                    search_progress.visibility = View.VISIBLE
                    val `in` = this@SearchActivity
                            .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    `in`.hideSoftInputFromWindow(search_edit.getWindowToken(), 0)
                    presenter.getData(search_edit.text.toString())
                }
            }
            true
        }


       presenter.list.observe(this, object : Observer<List<User>> {
            override fun onChanged(t: List<User>?) {
                if (t?.size == 0) {
                    Toast.makeText(this@SearchActivity, "NO SEARCH RESULTS"
                            , Toast.LENGTH_SHORT).show()
                }
                search_progress.visibility = View.GONE
                var adapter = SearchAdapter(this@SearchActivity, t!!)
                search_list.adapter = adapter
            }
        })


    }
}