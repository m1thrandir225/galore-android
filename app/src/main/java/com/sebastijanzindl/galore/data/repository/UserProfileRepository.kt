package com.sebastijanzindl.galore.data.repository

import com.sebastijanzindl.galore.domain.models.UserProfile

interface UserProfileRepository {
    suspend fun getCurrentUserProfile(): UserProfile?
}