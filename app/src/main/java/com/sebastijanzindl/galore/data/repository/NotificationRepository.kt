package com.sebastijanzindl.galore.data.repository

import com.sebastijanzindl.galore.domain.models.UserFCMToken

interface NotificationRepository {
    suspend fun registerFCMToken(token: String, userId: String): UserFCMToken?
}