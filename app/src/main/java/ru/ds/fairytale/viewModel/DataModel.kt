package ru.ds.fairytale.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DataModel : ViewModel() {

    val titleMessage: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
}