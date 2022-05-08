package ru.ds.fairytale

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import coil.load
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


        //отлавливаем токен
        FirebaseMessaging.getInstance().token.addOnCompleteListener { it ->
            if (it.isSuccessful) {
                Log.d("Dimas", it.result.toString())
            }

        }
        initBottomNavigation()
    }

  // private fun initMessage() {
  //     binding.title.text = intent.getStringExtra("title")
  //     binding.description.text = intent.getStringExtra("message")
  //     binding.imageView.load(intent.getIntExtra("message",0))
  // }



    private fun initBottomNavigation() {

        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.bottom_local -> {

                    supportFragmentManager
                        .beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.container, CoordinatorFragment())
                        .commit()
                    true
                }

                else -> true
            }
        }
        //default view
        binding.bottomNavigationView.selectedItemId = R.id.bottom_local
    }

    private fun downloadImage() {

        val image =startActivity(Intent(Intent.ACTION_VIEW).apply {
            data =
                Uri.parse(
                    //"https://firebasestorage.googleapis.com/v0/b/fairytale-cc1c4.appspot.com/o/test%2Fic_fisherman.png?alt=media&token=df7301c0-8934-4b71-bed0-d2a0c29a8a18"${binding.inputEditText.text.toString()}")
                    "https://firebasestorage.googleapis.com/v0/b/fairytale-cc1c4.appspot.com/o/test%2Fic_fisherman.png?alt=media&token=df7301c0-8934-4b71-bed0-d2a0c29a8a18")
        })

    }

}


