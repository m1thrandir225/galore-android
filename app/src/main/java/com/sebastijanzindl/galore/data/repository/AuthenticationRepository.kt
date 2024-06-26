package com.sebastijanzindl.galore.data.repository

interface AuthenticationRepository {
    suspend fun signIn(email: String, password: String): Boolean
    suspend fun signUp(userEmail: String, userPassword: String, fullName: String, avatarUrl: String): Boolean
    suspend fun signInWithGoogle(token: String, rawNonce: String): Boolean
    suspend fun signOut(): Boolean
}