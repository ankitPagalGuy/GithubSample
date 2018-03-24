package com.example.ankit.githubsample.data

/**
 * Created by ankit on 24/03/18.
 */

data class Search(var total_count: Long, var incomplete_result: Boolean, var items: List<User>)