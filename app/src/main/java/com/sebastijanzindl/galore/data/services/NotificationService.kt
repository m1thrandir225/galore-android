package com.sebastijanzindl.galore.data.services

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.NotificationManager.IMPORTANCE_DEFAULT
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_IMMUTABLE
import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.sebastijanzindl.galore.GaloreActivity
import com.sebastijanzindl.galore.R
import com.sebastijanzindl.galore.domain.usecase.RegisterFCMTokenUseCase
import com.sebastijanzindl.galore.presentation.util.deviceId
import dagger.hilt.android.AndroidEntryPoint
import io.github.jan.supabase.gotrue.Auth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@AndroidEntryPoint
class NotificationService: FirebaseMessagingService() {

    @Inject
    lateinit var auth: Auth

    @Inject
    lateinit var registerFCMTokenUseCase: RegisterFCMTokenUseCase

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    val context: Context = this

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        remoteMessage.notification?.let { message ->
            sendNotification(message)
        }
    }
    override fun onNewToken(token: String) {
        registerUserToken(token)
    }

    private fun registerUserToken(token: String) {
        coroutineScope.launch {
            try {
                val user = auth.currentUserOrNull() ?: throw Exception("User not signed in.")



                val response = registerFCMTokenUseCase.execute(
                    RegisterFCMTokenUseCase.Input(token = token, userId = user.id, context.deviceId())
                )

                when(response) {
                    RegisterFCMTokenUseCase.Output.Success -> {
                        return@launch;
                    }

                    RegisterFCMTokenUseCase.Output.Failure -> {
                        throw Exception("There was an error registering the new token.")
                    }
                }
            } catch (e: Exception) {
                Log.e("FCM Service", e.message.toString())
            }


        }
    }

    private fun sendNotification(message: RemoteMessage.Notification) {
        val intent = Intent(this, GaloreActivity::class.java).apply {
            addFlags(FLAG_ACTIVITY_CLEAR_TOP)
        }

        val pendingIntent = PendingIntent.getActivity(
            this, 0, intent, FLAG_IMMUTABLE
        )

        val channelId = this.getString(R.string.default_notification_channel_id)

        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setContentTitle(message.title)
            .setContentText(message.body)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)

        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        val channel = NotificationChannel(channelId, "galore_chanel", IMPORTANCE_DEFAULT)
        manager.createNotificationChannel(channel)

        manager.notify(Random.nextInt(), notificationBuilder.build())

    }
}