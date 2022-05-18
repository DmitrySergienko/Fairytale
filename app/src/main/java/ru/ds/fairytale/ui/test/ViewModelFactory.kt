package ru.ds.fairytale.ui.test

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.ds.fairytale.retrofit.FirebaseRep


class ViewModelFactory(private val item: FirebaseRep) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FirebaseViewModel(item) as T
    }
}