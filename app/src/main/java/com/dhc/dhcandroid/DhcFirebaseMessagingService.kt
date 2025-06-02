package com.dhc.dhcandroid

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.os.Build
import android.util.Log
import com.dhc.presentation.notification.NotificationUtil
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class DhcFirebaseMessagingService : FirebaseMessagingService() {

    private val notificationManager by lazy {
        applicationContext.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
    }

    override fun onCreate() {
        super.onCreate()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Channel title", // TODO - 추후 정의 필요
                NotificationManager.IMPORTANCE_HIGH,
            )
            notificationManager.createNotificationChannel(channel)
        }
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d(TAG, "onNewToken: Refreshed token= $token")
        // 서버에 전송 로직 추가 필요
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Log.d(TAG, "onMessageReceived: From= ${remoteMessage.from}")

        if (remoteMessage.data.isNotEmpty()) {
            Log.d(TAG, "onMessageReceived: Message data payload= ${remoteMessage.data}")
            val title = remoteMessage.data.getOrDefault("title", "")
            val message = remoteMessage.data.getOrDefault("body", "")

            sendNotification(title, message)
        }
    }

    private fun sendNotification(title: String, messageBody: String) {
        val intent = Intent(
            this,
            MainActivity::class.java,
        ).apply { addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP) }
        NotificationUtil.sendNotification(
            context = this,
            notificationManager = notificationManager,
            title = title,
            message = messageBody,
            intent = intent,
            iconResId = R.mipmap.ic_launcher,
        )
    }

    companion object {
        private const val TAG = "DhcFirebaseMessagingService"
        private const val CHANNEL_ID = "DhcChannel"
    }
}
