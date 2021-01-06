package com.example.architecturebase

import com.example.architecturebase.network.IPostApi

interface IRepository {

    fun getData(): IPostApi
}