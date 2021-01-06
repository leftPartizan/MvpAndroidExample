package com.example.architecturebase

import android.widget.Toast
import com.example.architecturebase.UseCases.UseCaseGetPosts
import com.example.architecturebase.adapter.MainAdapter
import com.example.architecturebase.databinding.ActivityMainBinding
import com.example.architecturebase.network.model.Post
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MvpPresenter(private val view: MvpContract.IView) : MvpContract.IPresenter {

    private val repository : IRepository = Repository

    private val mainAdapter = MainAdapter()

    private val binding by lazy {
        val bind = ActivityMainBinding.inflate(view.getLayoutInflater())
        view.setContentView(bind.root)
        bind
    }

    override fun getPosts() {
        repository.getData().getPosts().enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (response.isSuccessful) {
                    response.body()?.let { posts ->
                        mainAdapter.items = UseCaseGetPosts(posts).invoke()
                        binding.listSRL.isRefreshing = false
                    }
                }
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                view.showFailureLoadDataDialog(t)
                t.printStackTrace()
                binding.listSRL.isRefreshing = false
            }
        })
    }

    override fun setLayoutManagerWithAdapter() {
        binding.mainRV.apply {
            layoutManager = view.getLayoutManager()
            adapter = mainAdapter
        }
    }

    override fun setListRefreshing() {
        binding.listSRL.isRefreshing = true
    }

    override fun setListenerListRefresher() {
        binding.listSRL.setOnRefreshListener{
            mainAdapter.items = emptyList()
            getPosts()
        }
    }
}