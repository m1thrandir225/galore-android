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
    override suspend fun signUp(email: String, password: String, fullName: String): Boolean {
        val firstName = fullName.split(" ")[0];
        val lastName = fullName.split(" ")[1];
        return try {
            auth.signUpWith(Email) {
                this.email = email
                this.password = password
                data = buildJsonObject {
                    put("first_name", firstName)
                    put("last_name", lastName)
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

    override suspend fun getLoggedInUserID(): String {
        return try {
           val user = auth.currentUserOrNull();
            if(user?.id == null) {
                throw Exception("The user is not signed in")
            }
            return user.id
        } catch (e: Exception) {
            ""
        }
    }
}