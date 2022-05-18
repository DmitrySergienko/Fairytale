package ru.ds.fairytale.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DataModel : ViewModel() {

    val imageMessage: MutableLiveData<String> by lazy {
        MutableLiveData<String>()

    }
  // val titleMessage: MutableLiveData<String> by lazy {
  //     MutableLiveData<String>()

  // }
    val messageMessage: MutableLiveData<String> by lazy {
        MutableLiveData<String>()

    }
}