package ru.ds.fairytale

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import coil.load
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

        initMessage()//выводим сообщения на экран

        //отлавливаем токен
        FirebaseMessaging.getInstance().token.addOnCompleteListener { it ->
            if (it.isSuccessful) {
                Log.d("Dimas", it.result.toString())
            }

        }

    }

    private fun initMessage() {
        binding.title.text = intent.getStringExtra("title")
        binding.description.text = intent.getStringExtra("message")
        binding.imageView.load(intent.getIntExtra("message",0))
    }

}


