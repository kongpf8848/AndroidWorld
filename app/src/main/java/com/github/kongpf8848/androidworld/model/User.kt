package com.github.kongpf8848.androidworld.model

import androidx.databinding.BaseObservable

data class User(var name: String, var age: Int) : BaseObservable() {

    fun updateName(name: String) {
        this.name = name
        notifyChange()
    }
}
