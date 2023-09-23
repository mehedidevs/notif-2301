package com.tscore.sports.notificationtst

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class NotificationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        val tv = findViewById<TextView>(R.id.showTv)
        tv.text = intent.getStringExtra("key")


    }
}