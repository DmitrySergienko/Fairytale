package ru.ds.fairytale

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.messaging.FirebaseMessaging
import ru.ds.fairytale.coordianator.CoordinatorFragment
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

        supportFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .replace(R.id.container, CoordinatorFragment())
            .commit()

        //отлавливаем токен
        FirebaseMessaging.getInstance().token.addOnCompleteListener { it ->
            if (it.isSuccessful) {
                Log.d("Dimas", it.result.toString())
            }

        }

    }

}


