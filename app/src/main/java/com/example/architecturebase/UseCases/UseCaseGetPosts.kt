package com.example.architecturebase.UseCases

import com.example.architecturebase.network.model.Post

class UseCaseGetPosts(private val posts: List<Post>) {

    operator fun invoke(): List<Post> {
        return posts.filter {
            !it.title.startsWith("H")
        }.map {
            it.copy(title = it.title + "appendix")
        }.sortedBy {
            it.title
        }.subList(0, posts.size - 3)
    }

}