package com.example.architecturebase.UseCases

import androidx.lifecycle.MutableLiveData
import com.example.architecturebase.IRepository
import com.example.architecturebase.Repository
import com.example.architecturebase.network.model.Post
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object UseCaseGetPosts {

    private val repository: IRepository = Repository()

    fun loadPosts(listPosts: MutableLiveData<List<Post>>, errorMessage: MutableLiveData<Throwable>) {
        repository.getData().getPosts().enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (response.isSuccessful) {
                    response.body()?.let { posts ->
                        listPosts.value = sortedPosts(posts)
                    }
                }
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                errorMessage.value = t
            }
        })
    }

    private fun sortedPosts(posts: List<Post>): List<Post> {
        return posts.filter {
            !it.title.startsWith("H")
        }.map {
            it.copy(title = it.title + "appendix")
        }.sortedBy {
            it.title
        }.subList(0, posts.size - 3)
    }
}