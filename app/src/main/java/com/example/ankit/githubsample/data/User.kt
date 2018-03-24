package com.example.ankit.githubsample.data

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by ankit on 24/03/18.
 */

@Entity
data class User(var login: String, @PrimaryKey var id: Long)