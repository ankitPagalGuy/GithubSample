package com.example.ankit.githubsample.ui.showusers

import android.support.v7.recyclerview.extensions.DiffCallback
import com.example.ankit.githubsample.data.User

/**
 * Created by ankit on 24/03/18.
 */

class DiffCallBack {

    companion object {
        fun provideDiffCallBack(): DiffCallback<User> {
            return object : DiffCallback<User>() {
                override fun areContentsTheSame(oldItem: User, newItem: User): Boolean =
                        oldItem.id == newItem.id

                override fun areItemsTheSame(oldItem: User, newItem: User): Boolean =
                        oldItem == newItem
            }
        }
    }
}