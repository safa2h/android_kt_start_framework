package com.devsa.notify_app.framework

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.uncox.learn.notifyapp.framework.PermissionHandler

abstract class XActivity : AppCompatActivity() {

    val permissionHandlers = mutableListOf<PermissionHandler>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        G.currentActivity = this
        G.context = this
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        G.currentActivity = this
        G.context = this
        createNotificationChannel()
    }

    override fun onResume() {
        super.onResume()
        G.currentActivity = this
        G.context = this
    }

    override fun onStart() {

        super.onStart()

        G.currentActivity = this
        G.context = this

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {


        permissionHandlers.forEach {
            val handeld = it.processOnPermissionResult(requestCode, permissions, grantResults)
            if (handeld) {

                return
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

    }

    private fun createNotificationChannel() {
        NotificationHandler.NotificationChannel("Default") {
            name = "Default"
            descriptionChannel = "for default"

        }
    }


}