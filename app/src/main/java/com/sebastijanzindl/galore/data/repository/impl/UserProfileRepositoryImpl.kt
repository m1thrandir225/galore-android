package com.sebastijanzindl.galore.data.repository.impl

import com.sebastijanzindl.galore.data.repository.UserProfileRepository
import com.sebastijanzindl.galore.domain.models.UserProfile
import io.github.jan.supabase.functions.Functions
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.postgrest.Postgrest
import javax.inject.Inject

class UserProfileRepositoryImpl @Inject constructor(
    private val postgrest: Postgrest,
    private val auth: Auth,
    private val functions: Functions
): UserProfileRepository{
    override suspend fun getCurrentUserProfile(): UserProfile {
        //Wait for the plugin first to load
        auth.awaitInitialization();

        val userInfo = auth.retrieveUserForCurrentSession(updateSession = true);

        return postgrest.from("profiles")
            .select()
            {
                filter {
                    eq("id", userInfo.id)
                }
            }.decodeSingle<UserProfile>()
    }

    override suspend fun deleteUserProfile(): Boolean {
        auth.awaitInitialization()
        val response =  functions.invoke("delete-account")
        if(response.status.value in  200..299) {
            auth.clearSession()
            return true;
        } else {
            return false;
        }
    }

    override suspend fun updateUserProfile(updatedProfile: UserProfile): UserProfile {
        return postgrest.from("profiles")
            .update({
                set("full_name", updatedProfile.fullName)
                set("email", updatedProfile.email)
            }) {
                select()
                filter {
                    eq("id", updatedProfile.id)
                }
            }.decodeSingle<UserProfile>()
    }
}