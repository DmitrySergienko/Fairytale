package ru.ds.fairytale

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessaging
import ru.ds.fairytale.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object {

        private const val CHANNEL_ID_1 = "Channel_Id_1"
        private const val CHANNEL_ID_2 = "Channel_Id_2"
        private const val NOTIFICATION_ID_1 = 1
        private const val NOTIFICATION_ID_2 = 2

    }

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.title.text = intent.getStringExtra("title")
        binding.description.text = intent.getStringExtra("message")

        //pushNotification() //создаем каналы для внутрисистемных сообщений

        //отлавливаем токен
        FirebaseMessaging.getInstance().token.addOnCompleteListener { it ->
            if (it.isSuccessful) {
                Log.d("Dimas", it.result.toString())
            }

        }

    }

    private fun pushNotification() {
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val notificationBuilder_1 = NotificationCompat.Builder(this, CHANNEL_ID_1).apply {
            setSmallIcon(R.drawable.ic_launcher_background)
            setContentTitle("Заголовок $CHANNEL_ID_1")
            setContentText("Заголовок $CHANNEL_ID_1")
            priority = NotificationCompat.PRIORITY_MAX
        }
        val notificationBuilder_2 = NotificationCompat.Builder(this, CHANNEL_ID_2).apply {
            setSmallIcon(R.drawable.ic_launcher_background)
            setContentTitle("Заголовок $CHANNEL_ID_2")
            setContentText("Заголовок $CHANNEL_ID_2")
            priority = NotificationCompat.PRIORITY_MAX
        }
        //создаем канал 1
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val nameChannel_1 = "Name $CHANNEL_ID_1"
            val descrChannel_1 = "Description $CHANNEL_ID_1"
            val importanceChannel_1 = NotificationManager.IMPORTANCE_MIN
            val channel_1 =
                NotificationChannel(CHANNEL_ID_1, nameChannel_1, importanceChannel_1).apply {
                    description = descrChannel_1
                }
            notificationManager.createNotificationChannel(channel_1)
        }
        notificationManager.notify(NOTIFICATION_ID_1, notificationBuilder_1.build())
        //создаем канал 2
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val nameChannel_2 = "Name $CHANNEL_ID_2"
            val descrChannel_2 = "Description $CHANNEL_ID_2"
            val importanceChannel_2 = NotificationManager.IMPORTANCE_HIGH
            val channel_2 =
                NotificationChannel(CHANNEL_ID_2, nameChannel_2, importanceChannel_2).apply {
                    description = descrChannel_2
                }
            notificationManager.createNotificationChannel(channel_2)
        }
        notificationManager.notify(NOTIFICATION_ID_2, notificationBuilder_2.build())
    }
}