package com.example.ankit.githubsample.ui.showusers

import android.arch.paging.PagedListAdapter
import android.content.Context
import android.support.v7.recyclerview.extensions.DiffCallback
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


class AllUsersAdapter(diff: DiffCallback<User>, var context: Context) :
        PagedListAdapter<User, RecyclerView.ViewHolder>(diff) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.single_user, parent, false)
        return AllUsersViewHolder(view)

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        holder.itemView.user_name.text = item?.login


    }

    class AllUsersViewHolder(item: View) : RecyclerView.ViewHolder(item) {


    }
}