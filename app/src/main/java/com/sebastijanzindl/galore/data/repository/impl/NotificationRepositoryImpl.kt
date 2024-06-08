package com.sebastijanzindl.galore.data.repository.impl

import com.sebastijanzindl.galore.data.repository.NotificationRepository
import com.sebastijanzindl.galore.domain.models.UserFCMToken
import com.sebastijanzindl.galore.domain.models.UserFCMTokenRequest
import io.github.jan.supabase.postgrest.Postgrest
import javax.inject.Inject

class NotificationRepositoryImpl @Inject constructor(
    private val postgrest: Postgrest,
) : NotificationRepository {
    override suspend fun registerFCMToken(token: String, userId: String):  UserFCMToken? {
        return postgrest.from("user_fcm_tokens")
            .insert(
                UserFCMTokenRequest(
                    userId = userId,
                    fcmToken = token
                )
            ) {
                select()
            }.decodeSingleOrNull<UserFCMToken>()
    }
}