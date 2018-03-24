package com.example.ankit.githubsample.ui.search

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ankit.githubsample.R
import com.example.ankit.githubsample.data.User
import kotlinx.android.synthetic.main.single_user.view.*

/**
 * Created by ankit on 24/03/18.
 */

class SearchAdapter(var context: Context, var list: List<User>)
    : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):SearchViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.single_user, parent, false)

        return SearchViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.itemView.user_name.setText(list.get(position).login)
    }


    class SearchViewHolder(item: View) : RecyclerView.ViewHolder(item) {


    }
}