package com.example.appmodoguardian_grupo1.repository

import com.example.appmodoguardian_grupo1.model.Post
import com.example.appmodoguardian_grupo1.remote.RetrofitInstance

class PostRepository {
    private val api = RetrofitInstance.api

    suspend fun getPosts(): List<Post> = api.getPosts()
    suspend fun createPost(post: Post) = api.createPost(post)
    suspend fun updatePost(id: Int, post: Post) = api.updatePost(id, post)
    suspend fun deletePost(id: Int) = api.deletePost(id)
}