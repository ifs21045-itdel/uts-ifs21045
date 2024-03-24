// Mendefinisikan paket dari kelas ini
package com.ifs21045.dinopedia

// Mengimpor kelas yang diperlukan dari aplikasi dinopedia
import com.ifs21045.dinopedia.Tyrannosaurus

// Mengimpor kelas-kelas yang diperlukan dari library Android
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ifs21045.dinopedia.databinding.ItemRowTyrannosaurusBinding

// Mendefinisikan kelas ListTyrannosaurusAdapter yang merupakan turunan dari RecyclerView.Adapter
class ListTyrannosaurusAdapter(private val listTyrannosaurus: ArrayList<Tyrannosaurus>) :
    RecyclerView.Adapter<ListTyrannosaurusAdapter.ListViewHolder>() {

    // Mendeklarasikan lateinit property untuk menyimpan callback onClick
    private lateinit var onItemClickCallback: OnItemClickCallback

    // Metode untuk mengatur callback onClick
    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    // Override metode onCreateViewHolder untuk membuat ViewHolder
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListViewHolder {
        // Membuat binding untuk tampilan item daftar tyrannosaurus
        val binding = ItemRowTyrannosaurusBinding.inflate(LayoutInflater.from(viewGroup.context),
            viewGroup, false)
        // Mengembalikan ViewHolder baru
        return ListViewHolder(binding)
    }

    // Override metode onBindViewHolder untuk mengikat data ke ViewHolder
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        // Mendapatkan objek Tyrannosaurus dari posisi yang diberikan
        val tyrannosaurus = listTyrannosaurus[position]
        // Mengatur gambar dan teks pada tampilan item
        holder.binding.ivItemTyrannosaurus.setImageResource(tyrannosaurus.icon)
        holder.binding.tvItemTyrannosaurus.text = tyrannosaurus.name
        // Menetapkan listener onClick untuk item
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listTyrannosaurus[holder.adapterPosition])
        }
    }

    // Override metode getItemCount untuk mengembalikan jumlah item dalam daftar
    override fun getItemCount(): Int = listTyrannosaurus.size

    // Mendefinisikan kelas inner ViewHolder
    class ListViewHolder(var binding: ItemRowTyrannosaurusBinding) :
        RecyclerView.ViewHolder(binding.root)

    // Interface untuk menangani klik item dalam RecyclerView
    interface OnItemClickCallback {
        fun onItemClicked(data: Tyrannosaurus)
    }
}
