package com.example.architecturebase

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.architecturebase.adapter.MainAdapter
import com.example.architecturebase.databinding.FragmentBinding


class Fragment : Fragment(R.layout.fragment) {

    private val mvvmModelView: ViewModelMvvm = ViewModelMvvm()

    init {
        lifecycle.addObserver(mvvmModelView)
    }

    private lateinit var binding: FragmentBinding

    private val mainAdapter = MainAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBinding.bind(view)

        binding.mainRV.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = mainAdapter
        }

        binding.listSRL.isRefreshing = true

        mvvmModelView.listPosts.observe(viewLifecycleOwner, Observer {
            it.let {
                mainAdapter.items = it
                binding.listSRL.isRefreshing = false
            }
        })

        mvvmModelView.errorMessage.observe(
                viewLifecycleOwner,
                Observer { t -> showFailureLoadDataDialog(t) }
        )

        binding.listSRL.setOnRefreshListener {
            mainAdapter.items = emptyList()
            mvvmModelView.getPosts()
        }
    }

    private fun showFailureLoadDataDialog(t: Throwable) {
        t.printStackTrace()
        binding.listSRL.isRefreshing = false
        Toast.makeText(activity, t.message, Toast.LENGTH_SHORT).show()
    }
}