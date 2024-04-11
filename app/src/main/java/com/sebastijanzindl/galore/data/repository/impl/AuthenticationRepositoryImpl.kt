package com.sebastijanzindl.galore.data.repository.impl

import com.sebastijanzindl.galore.data.repository.AuthenticationRepository
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.gotrue.providers.Google
import io.github.jan.supabase.gotrue.providers.builtin.Email
import io.github.jan.supabase.gotrue.providers.builtin.IDToken
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import javax.inject.Inject
import kotlin.Exception

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
    override suspend fun signUp(userEmail: String, userPassword: String, fullName: String): Boolean {
        return try {
            auth.signUpWith(Email) {
                email = userEmail
                password = userPassword
                data = buildJsonObject {
                    put("full_name", fullName)
                    put("email", email)
                    put("avatar_url", "")
                }
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

    override suspend fun signOut(): Boolean {
       return try {
           auth.signOut();
           true;
       } catch (e: Exception) {
           false
       }
    }
}