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
        /**
         * 1. await auth plugin intialization
         * 2. get the response from the edge-function "delete-account"
         * 3. if the response status code is in the range 200 -> 299 clear the session and return true
         * 4. if the response status code is not in the range return false
         */
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
                set("push_notifications", updatedProfile.pushNotifications)
                set("email_notifications", updatedProfile.emailNotifications)
                set("avatar_url", updatedProfile.avatarUrl)
            }) {
                select()
                filter {
                    eq("id", updatedProfile.id)
                }
            }.decodeSingle<UserProfile>()
    }
}