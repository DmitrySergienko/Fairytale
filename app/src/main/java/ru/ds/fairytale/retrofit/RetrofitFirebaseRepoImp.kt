package ru.ds.fairytale.retrofit

import io.reactivex.rxjava3.core.Single
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitFirebaseRepoImp: FirebaseRep {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://firebasestorage.googleapis.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val api: FirebaseApi = retrofit.create(FirebaseApi::class.java)

    override fun getData(): Single<String> {
        return Single.create { emitter ->
            api.getDataFromFirebase()
                .enqueue(object : Callback<String> {
                    override fun onResponse(
                        call: Call<String>,
                        response: Response<String>
                    ) {
                        emitter.onSuccess(response.body())
                    }

                    override fun onFailure(call: Call<String>, t: Throwable) {
                        emitter.onError(t)
                    }
                })
        }
    }


}
