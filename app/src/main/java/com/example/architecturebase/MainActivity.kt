package com.example.architecturebase

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.architecturebase.UseCases.UseCaseGetPosts
import com.example.architecturebase.adapter.MainAdapter
import com.example.architecturebase.databinding.ActivityMainBinding
import com.example.architecturebase.network.IPostApi
import com.example.architecturebase.network.model.Post
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity(), MvpContract.IView {

    private val presenter: MvpContract.IPresenter = MvpPresenter(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter.setLayoutManagerWithAdapter()

        presenter.setListRefreshing()

        presenter.getPosts()

        presenter.setListenerListRefresher()
    }

    override fun showFailureLoadDataDialog(t: Throwable) {
        Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
    }

    override fun getLayoutManager(): LinearLayoutManager {
        return LinearLayoutManager(this)
    }


}