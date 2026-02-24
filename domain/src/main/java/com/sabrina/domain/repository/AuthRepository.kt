package com.sabrina.domain.repository

import com.sabrina.domain.model.User

interface AuthRepository {
    val currentUser: User?

    suspend fun login(email: String, password: String) : Result<Unit>

    suspend fun signUp(email: String, password: String) : Result<Unit>
    fun logout()
}