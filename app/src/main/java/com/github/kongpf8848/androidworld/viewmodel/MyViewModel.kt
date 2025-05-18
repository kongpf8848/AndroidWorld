package com.github.kongpf8848.androidworld.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {
    val userName = MutableLiveData("John")

}