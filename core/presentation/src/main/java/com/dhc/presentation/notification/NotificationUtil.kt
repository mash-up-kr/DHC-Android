package com.dhc.presentation.notification

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat

object NotificationUtil {

    fun sendNotification(
        context: Context,
        notificationManager: NotificationManager,
        title: String,
        message: String,
        intent: Intent? = null,
        notificationId: Int = DEFAULT_NOTIFICATION_ID,
        iconResId: Int,
    ) {

        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS,
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            Log.i(TAG, "sendNotification: message Fail, no permission")
            return
        }

        val pendingIntent = intent?.let {
            PendingIntent.getActivity(
                context,
                0,
                it.apply { addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP) },
                PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT,
            )
        }
        
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        

        val notificationBuilder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(iconResId)
            .setContentTitle(title)
            .setContentText(message)
            .setSound(defaultSoundUri)
            .setPriority(NotificationCompat.PRIORITY_HIGH)

        pendingIntent?.let {
            notificationBuilder.setContentIntent(it)
        }

        notificationManager.notify(notificationId, notificationBuilder.build())
    }

    private const val TAG = "NotificationUtil"
    private const val CHANNEL_ID = "DhcChannel"
    private const val DEFAULT_NOTIFICATION_ID = 0


}
