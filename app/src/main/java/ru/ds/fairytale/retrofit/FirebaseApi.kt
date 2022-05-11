package ru.ds.fairytale.retrofit


import retrofit2.Call
import retrofit2.http.GET

interface FirebaseApi {

    @GET("https://firebasestorage.googleapis.com/v0/b/fairytale-cc1c4.appspot.com/o/The_fisherman_and_gold_fish.txt?alt=media&token=18fd106c-f7a2-4c67-869d-03ceddbe3632")
    fun getDataFromFirebase(): Call<String>
}