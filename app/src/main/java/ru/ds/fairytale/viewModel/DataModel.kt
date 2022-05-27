package ru.ds.fairytale.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.net.URL
import java.nio.charset.Charset

class DataModel : ViewModel() {

    val imageMessage: MutableLiveData<String> by lazy {
        MutableLiveData<String>()

    }
    val messageMessage: MutableLiveData<String> by lazy {
        MutableLiveData<String>()

    }

    private val _text = MutableLiveData<String>()
    val text: LiveData<String> = _text

    fun getTextFromURL(dataFromFireBase: String){
        viewModelScope.launch {
            _text.value =
                URL(dataFromFireBase)
                    .readText(Charset.forName("UTF-8"))
        }
    }

}