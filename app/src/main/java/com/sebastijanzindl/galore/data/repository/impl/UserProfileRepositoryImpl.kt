package com.sebastijanzindl.galore.data.repository.impl

import com.sebastijanzindl.galore.data.repository.UserProfileRepository
import com.sebastijanzindl.galore.domain.models.UserProfile
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.gotrue.SessionStatus
import io.github.jan.supabase.postgrest.Postgrest
import javax.inject.Inject

class UserProfileRepositoryImpl @Inject constructor(
    private val postgrest: Postgrest,
    private val auth: Auth
): UserProfileRepository{
    override suspend fun getCurrentUserProfile(): UserProfile? {
            val user= auth.currentUserOrNull();

            return postgrest.from("profiles")
                .select()
                {
                    filter {
                        user?.id?.let { eq("id", it) }
                    }
                }.decodeSingle<UserProfile>()

    }
}