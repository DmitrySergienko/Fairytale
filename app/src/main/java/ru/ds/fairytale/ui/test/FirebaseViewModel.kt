package ru.ds.fairytale.ui.test

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import ru.ds.fairytale.retrofit.FirebaseRep


class FirebaseViewModel(private val firebaseRep: FirebaseRep) : ViewModel() {
    private val _repo = MutableLiveData<String>()
    val repo: LiveData<String> = _repo

    //для отписки
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    //метод - подписка на обновления с сервера
    fun onShowData() {
        compositeDisposable
            .add(firebaseRep
                .getData()
                .subscribeBy { _repo.postValue(it)} // как только приходит результат отправляем его
        )
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}