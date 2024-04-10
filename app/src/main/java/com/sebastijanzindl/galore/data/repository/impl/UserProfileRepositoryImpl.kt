package com.sebastijanzindl.galore.data.repository.impl

import android.util.Log
import com.sebastijanzindl.galore.data.repository.UserProfileRepository
import com.sebastijanzindl.galore.domain.models.UserProfile
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.gotrue.SessionStatus
import io.github.jan.supabase.postgrest.Postgrest

import javax.inject.Inject
import kotlin.Exception

class UserProfileRepositoryImpl @Inject constructor(
    private val postgrest: Postgrest,
    private val auth: Auth
): UserProfileRepository{
    override suspend fun getCurrentUserProfile(): UserProfile? {
        try {
            val userInfo = auth.retrieveUserForCurrentSession(updateSession = true);

            return postgrest.from("profiles")
                .select()
                {
                    filter {
                        eq("id", userInfo.id)
                    }
                }.decodeSingle<UserProfile>()
        } catch(e: Exception) {
            e.message?.let { Log.i("Error", it) }
        }

        return null
    }
}