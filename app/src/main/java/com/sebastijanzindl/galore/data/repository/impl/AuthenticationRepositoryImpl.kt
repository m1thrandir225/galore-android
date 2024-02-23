package com.sebastijanzindl.galore.data.repository.impl

import androidx.compose.ui.platform.LocalContext
import com.sebastijanzindl.galore.data.repository.AuthenticationRepository
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.gotrue.providers.Google
import io.github.jan.supabase.gotrue.providers.builtin.Email
import io.github.jan.supabase.gotrue.providers.builtin.IDToken
import java.lang.Exception
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(
    private val auth: Auth
) : AuthenticationRepository{
    override suspend fun signIn(email: String, password: String): Boolean {
        return try {
            auth.signInWith(Email) {
                this.email = email
                this.password = password
            }
            true
        } catch (e: Exception) {
            false
        }
    }
    override suspend fun signUp(email: String, password: String): Boolean {
        return try {
            auth.signUpWith(Email) {
                this.email = email
                this.password = password
            }
            true
        } catch (e: Exception) {
            false
        }
    }

    override suspend fun signInWithGoogle(token: String, rawNonce: String): Boolean {
        return try {
            auth.signInWith(IDToken) {
                idToken = token
                provider = Google
                nonce = rawNonce
            }
            true
        } catch (e: Exception) {
            println(e.message)
            false
        }
    }
}