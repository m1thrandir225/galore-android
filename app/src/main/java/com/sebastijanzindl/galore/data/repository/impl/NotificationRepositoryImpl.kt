package com.sebastijanzindl.galore.data.repository.impl

import com.sebastijanzindl.galore.data.repository.NotificationRepository
import com.sebastijanzindl.galore.domain.models.UserFCMToken
import com.sebastijanzindl.galore.domain.models.UserFCMTokenRequest
import io.github.jan.supabase.postgrest.Postgrest
import javax.inject.Inject

class NotificationRepositoryImpl @Inject constructor(
    private val postgrest: Postgrest,
) : NotificationRepository {
    override suspend fun registerFCMToken(token: String, userId: String, deviceId: String):  UserFCMToken? {

        val alreadyExists = postgrest.from("user_fcm_tokens")
            .select {
                filter {
                    eq("user_id", userId)
                    eq("token", token)
                    eq("device_id", deviceId)
                }
            }.decodeSingleOrNull<UserFCMToken>()

        return if(alreadyExists != null) {
            alreadyExists
        } else {
            postgrest.from("user_fcm_tokens")
                .insert(
                    UserFCMTokenRequest(
                        userId = userId,
                        fcmToken = token,
                        deviceID = deviceId
                    )
                ) {
                    select()
                }.decodeSingleOrNull<UserFCMToken>()
        }
    }
}