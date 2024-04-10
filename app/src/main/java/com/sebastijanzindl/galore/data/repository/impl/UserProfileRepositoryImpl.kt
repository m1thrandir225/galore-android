package com.sebastijanzindl.galore.data.repository.impl

import com.sebastijanzindl.galore.data.repository.UserProfileRepository
import com.sebastijanzindl.galore.domain.models.UserProfile
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.postgrest.Postgrest
import javax.inject.Inject

class UserProfileRepositoryImpl @Inject constructor(
    private val postgrest: Postgrest,
    private val auth: Auth
): UserProfileRepository{
    override suspend fun getCurrentUserProfile(userID: String): UserProfile {
        return try {
            val userId = auth.currentUserOrNull();

            if(userId != null) {
                return postgrest
            }
        } catch (e: Exception) {
            false
        }
    }
}