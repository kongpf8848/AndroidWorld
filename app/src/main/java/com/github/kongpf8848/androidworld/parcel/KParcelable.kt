package com.github.kongpf8848.androidworld.parcel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class KParcelable(val id: Int, val name: String) : Parcelable


