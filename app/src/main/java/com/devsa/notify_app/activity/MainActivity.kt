package com.devsa.notify_app.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.core.app.NotificationCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.devsa.notify_app.R
import com.devsa.notify_app.framework.AndroidPermission
import com.devsa.notify_app.framework.NotificationHandler
import com.devsa.notify_app.framework.XActivity
import com.uncox.learn.notifyapp.framework.PermissionHandler

class MainActivity : XActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
//        PermissionHandler(arrayListOf(AndroidPermission.POST_NOTIFICATIONS))
//
//        NotificationHandler.Builder(androidx.core.R.drawable.ic_call_answer) {
//
//            title = "safa"
//            content = "safa joon"
//            priority = NotificationCompat.PRIORITY_DEFAULT
//
//            addAction(
//                androidx.core.R.drawable.notification_bg_normal,
//                "Snooz",
//                null
//
//            )
//        }


    }


}