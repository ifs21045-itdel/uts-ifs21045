package com.ifs21045.dinopedia

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class Famili(
    var name: String,
    var icon: Int,
    var description: String,
    var periode: String,
    var karakteristik: String,
    var habitat: String,
    var klasifikasi: String,
    var startIndex: Int,
    var endIndex: Int
) : Parcelable