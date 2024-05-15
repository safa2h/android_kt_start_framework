package com.devsa.notify_app.framework

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.annotation.DrawableRes
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService
import com.devsa.notify_app.R

class NotificationHandler {
//need permission in android +13

    class Builder(@DrawableRes var icon: Int, props: MethodBlock<Builder> = {}) {
        companion object {
            private var lastNotificationId = 0
        }


        private var actions = mutableListOf<Action>()
        var notificationId: Int = ++lastNotificationId
        var priority: Int = NotificationCompat.PRIORITY_DEFAULT
        var channelId: String = "Default"
        var title: String = "notification"
        var content: String = "fill your notification content"

        init {
            this.props()
            show()
        }

        private data class Action(
            @DrawableRes var icon: Int,
            var caption: String,
            var pendingIntent: PendingIntent?
        )

        fun addAction(
            @DrawableRes icon: Int,
            caption: String,
            pendingIntent: PendingIntent?
        ) {
            actions.add(Action(icon, caption, pendingIntent))
        }

        private fun show() {
            val notification = NotificationCompat.Builder(G.currentActivity, channelId)
                .setSmallIcon(icon)
                .setContentText(title)
                .setContentText(content)
                .setPriority(priority)
                .apply {
                    actions.forEach {
                        addAction(it.icon, it.caption, it.pendingIntent)
                    }
                }


            with(NotificationManagerCompat.from(G.currentActivity)) {
                if (ActivityCompat.checkSelfPermission(
                        G.currentActivity,
                        AndroidPermission.POST_NOTIFICATIONS
                    ) != PackageManager.PERMISSION_GRANTED
                ) {

                    return@with
                }
                // notificationId is a unique int for each notification that you must define.
                notify(notificationId, notification.build())
            }

        }
    }


    class NotificationChannel(var id: String, props: MethodBlock<NotificationChannel> = {}) {

        var name: String = "Default channel"
        var descriptionChannel: String = "Default description"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        var enableLights = false
        var eanbleViration = false

        init {
            this.props()
            createNotificationChannel()
        }

        private fun createNotificationChannel() {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                val channel = NotificationChannel(id, name, importance).apply {
                    description = descriptionChannel
                    enableLights(enableLights)
                    enableVibration(eanbleViration)
                }
                val notificationManager =
                    G.context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.createNotificationChannel(channel)
            }
        }
    }


}