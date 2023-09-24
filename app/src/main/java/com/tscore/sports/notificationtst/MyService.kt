package com.tscore.sports.notificationtst

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyService : FirebaseMessagingService() {
    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d("TAG", "onNewToken: $token")

    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)


        Log.d(
            "TAG",
            "onMessageReceived:  ${message.notification?.title}, body :  ${message.notification?.body}"
        )

        shoNotification(message.notification?.title ?: "title", message.notification?.body ?: "body")


    }


    private fun shoNotification(title: String, body: String) {
        lateinit var chanel: NotificationChannel
        lateinit var builder: NotificationCompat.Builder

        val channelID = "com.tscore.sports.test-channel"
        val manager: NotificationManager =
            getSystemService(NOTIFICATION_SERVICE) as NotificationManager


        val intent = Intent(this, NotificationActivity::class.java)
        intent.putExtra("key", "Text from Notification")

        val pendingIntent =
            PendingIntent.getActivity(
                this,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            chanel = NotificationChannel(
                channelID,
                "test_notification",
                NotificationManager.IMPORTANCE_HIGH
            )
            chanel.enableLights(true)
            chanel.lightColor = Color.YELLOW
            chanel.enableVibration(true)
            manager.createNotificationChannel(chanel)

            builder = NotificationCompat.Builder(this, channelID)
                .setContentTitle(title)
                .setContentText(body)
                .setSmallIcon(R.drawable.ic_stat_name)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)


        } else {

            builder = NotificationCompat.Builder(this)
                .setContentTitle(title)
                .setContentText(body)
                .setSmallIcon(R.drawable.ic_stat_name)
                .setContentIntent(pendingIntent)
        }

        val id = System.currentTimeMillis().toInt()

        manager.notify(id, builder.build())
    }


}