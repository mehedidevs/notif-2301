package com.tscore.sports.notificationtst

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        findViewById<Button>(R.id.showNotificationBtn).setOnClickListener {

            lateinit var chanel: NotificationChannel
            lateinit var builder: NotificationCompat.Builder

            val channelID = "com.tscore.sports.test-channel"
            val manager: NotificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

            Toast.makeText(this, "Click", Toast.LENGTH_SHORT).show()

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
                    .setContentTitle("Notification Title")
                    .setSmallIcon(R.drawable.ic_stat_name)
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent)


            } else {

                builder = NotificationCompat.Builder(this)
                    .setContentTitle("Notification Title")
                    .setSmallIcon(R.drawable.ic_stat_name)
                    .setContentIntent(pendingIntent)
            }

            val id = System.currentTimeMillis().toInt()

            manager.notify(id, builder.build())

        }
    }


}