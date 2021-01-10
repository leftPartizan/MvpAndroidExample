package com.example.architecturebase

import androidx.lifecycle.MutableLiveData
import com.example.architecturebase.network.model.Post

interface MvvmContract {

    val listPosts: MutableLiveData<List<Post>>
    val errorMessage: MutableLiveData<Throwable>
    fun getPosts()
}