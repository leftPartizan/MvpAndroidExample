package com.example.architecturebase

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import com.example.architecturebase.UseCases.UseCaseGetPosts
import com.example.architecturebase.network.model.Post

class ViewModelMvvm : LifecycleObserver, MvvmContract {


    override val listPosts: MutableLiveData<List<Post>> = MutableLiveData()

    override val errorMessage: MutableLiveData<Throwable>
        get() = MutableLiveData()

    override fun getPosts() {
        UseCaseGetPosts.loadPosts(listPosts, errorMessage)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        getPosts()
    }


}