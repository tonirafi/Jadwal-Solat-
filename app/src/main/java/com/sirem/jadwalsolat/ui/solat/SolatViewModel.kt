package com.sirem.jadwalsolat.ui.solat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SolatViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Solat Fragment"
    }
    val text: LiveData<String> = _text
}