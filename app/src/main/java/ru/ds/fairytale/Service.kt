package ru.ds.fairytale

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


class Service : FirebaseMessagingService() {

    //токен вызывается один раз при первом запуске
    override fun onNewToken(token: String) {
        super.onNewToken(token)
        //отправили token на сервер
    }

    //сюда приходят сообщения из FB
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        val remoteMessageData = remoteMessage.data
        if (remoteMessageData.isNotEmpty()) {

            val title = remoteMessageData[PUSH_KEY_TITLE]
            val message = remoteMessageData[PUSH_KEY_MESSAGE]
            if (!title.isNullOrBlank() && !message.isNullOrBlank()) {
                pushNotification(title, message)
                val intentAction = Intent(this, MainActivity::class.java)
                intentAction.putExtra("title", title.toString())
                intentAction.putExtra("message", message.toString())
            }

        }
    }

    companion object {
        private const val PUSH_KEY_TITLE = "Title"
        private const val PUSH_KEY_MESSAGE = "Message"
        private const val CHANNEL_ID_1 = "Channel_Id_1"
        private const val NOTIFICATION_ID_1 = 1
    }

    private fun pushNotification(title: String, message: String) {
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val notificationBuilder_1 = NotificationCompat.Builder(this, CHANNEL_ID_1).apply {
            setSmallIcon(R.drawable.ic_launcher_background)
            setContentTitle(title)
            setContentText(message)

            priority = NotificationCompat.PRIORITY_HIGH
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val nameChannel_1 = "Name $CHANNEL_ID_1"
            val descrChannel_1 = "Description $CHANNEL_ID_1"
            val importanceChannel_1 = NotificationManager.IMPORTANCE_HIGH
            val channel_1 =
                NotificationChannel(CHANNEL_ID_1, nameChannel_1, importanceChannel_1).apply {
                    description = descrChannel_1
                }
            notificationManager.createNotificationChannel(channel_1)
        }
        notificationManager.notify(NOTIFICATION_ID_1, notificationBuilder_1.build())

    }
}