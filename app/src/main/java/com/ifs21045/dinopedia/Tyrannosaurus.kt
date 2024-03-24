package com.ifs21045.dinopedia

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Tyrannosaurus(
    var name: String,
    var icon: Int,
    // Mendefinisikan properti icon bertipe Int
    var deskripsi: String,
    var karakteristik: String,
    var kelompok: String,
    var habitat: String,
    var makanan: String,
    var panjang: String,
    var tinggi: String,
    var bobot: String,
    var kelemahan: String
) : Parcelable
