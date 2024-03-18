import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class Fruit(
    var name: String,
    var icon: Int,
    var deskripsi: String,
    var karakteristik: String,
) : Parcelable