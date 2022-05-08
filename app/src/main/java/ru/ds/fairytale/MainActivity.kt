package ru.ds.fairytale

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.messaging.FirebaseMessaging
import ru.ds.fairytale.coordianator.CoordinatorFragment
import ru.ds.fairytale.databinding.ActivityMainBinding
import ru.ds.fairytale.viewModel.DataModel

class MainActivity : AppCompatActivity() {


    private val dataModel:DataModel by viewModels()

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
        dataModel.titleMessage.value = intent.getStringExtra("title")


    }

}


