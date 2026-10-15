package com.example.appmodoguardian_grupo1.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appmodoguardian_grupo1.model.Post
import com.example.appmodoguardian_grupo1.repository.PostRepository
import kotlinx.coroutines.launch

class PostViewModel(
    private val repository: PostRepository = PostRepository()
) : ViewModel() {

    var posts by mutableStateOf<List<Post>>(emptyList())
        private set

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    init {
        fetchPosts()
    }

    fun fetchPosts() {
        viewModelScope.launch {
            isLoading = true
            errorMessage = null
            try {
                posts = repository.getPosts()
            } catch (e: Exception) {
                errorMessage = "Error de conexión: ${e.localizedMessage}"
            } finally {
                isLoading = false
            }
        }
    }
}