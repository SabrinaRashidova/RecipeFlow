package com.sabrina.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.sabrina.data.mapper.toDomain
import com.sabrina.domain.model.User
import com.sabrina.domain.repository.AuthRepository
import kotlinx.coroutines.tasks.await
import java.lang.Exception
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth
) : AuthRepository{
    override val currentUser: User?
        get() = auth.currentUser?.toDomain()

    override suspend fun login(
        email: String,
        password: String,
    ): Result<Unit> {
       return try {
           auth.signInWithEmailAndPassword(email,password).await()
           Result.success(Unit)
       }catch (e: Exception){
           Result.failure(e)
       }
    }

    override suspend fun signUp(
        email: String,
        password: String,
    ): Result<Unit> {
        return try {
            auth.createUserWithEmailAndPassword(email,password).await()
            Result.success(Unit)
        }catch (e: kotlin.Exception){
            Result.failure(e)
        }
    }

    override suspend fun signInAnonymously(): Result<Unit> {
        return try {
            auth.signInAnonymously().await()
            Result.success(Unit)
        } catch (e: Exception) { Result.failure(e)}
    }

    override fun logout() {
        auth.signOut()
    }
}