package com.example.architecturebase

import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView

interface MvpContract {

    interface IView {
        fun getLayoutInflater() : LayoutInflater
        fun showFailureLoadDataDialog(t: Throwable)
        fun getLayoutManager() : RecyclerView.LayoutManager
        fun setContentView(view: View)
    }

    interface IPresenter {
        fun setLayoutManagerWithAdapter()
        fun setListRefreshing()
        fun getPosts()
        fun setListenerListRefresher()

    }
}