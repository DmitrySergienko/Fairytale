package ru.ds.fairytale.retrofit

import io.reactivex.rxjava3.core.Single

interface FirebaseRep {
    fun getData(): Single<String>
}