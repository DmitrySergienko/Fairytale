package ru.ds.fairytale

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import ru.ds.fairytale.retrofit.FirebaseRep
import ru.ds.fairytale.retrofit.RetrofitFirebaseRepoImp

//создаем app для централизованного доступа к данным (чтобы не создавать реп в каждом актививи и фрагменте)
class App : Application() {

    val firebaseRep: FirebaseRep by lazy { RetrofitFirebaseRepoImp() }

}
val Context.app: App
    get() = applicationContext as App
//val Fragment.app: App
  //  get() = requireActivity().application as App