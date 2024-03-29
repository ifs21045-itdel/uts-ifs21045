// Mendefinisikan paket dari kelas ini
package com.ifs21045.dinopedia

// Mengimpor kelas-kelas yang diperlukan dari library Android
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ifs21045.dinopedia.databinding.ItemRowFamiliBinding

// Mendefinisikan kelas ListFamiliAdapter yang merupakan turunan dari RecyclerView.Adapter
class ListFamiliAdapter(private val listFamili: ArrayList<Famili>) :
    RecyclerView.Adapter<ListFamiliAdapter.ListViewHolder>() {

    // Mendeklarasikan lateinit property untuk menyimpan callback onClick
    private lateinit var onItemClickCallback: OnItemClickCallback

    // Metode untuk mengatur callback onClick
    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    // Override metode onCreateViewHolder untuk membuat ViewHolder
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListViewHolder {
        // Membuat binding untuk tampilan item daftar famili
        val binding = ItemRowFamiliBinding.inflate(LayoutInflater.from(viewGroup.context),
            viewGroup, false)
        // Mengembalikan ViewHolder baru
        return ListViewHolder(binding)
    }

    // Override metode onBindViewHolder untuk mengikat data ke ViewHolder
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        // Mendapatkan objek Famili dari posisi yang diberikan
        val famili = listFamili[position]
        // Mengatur gambar dan teks pada tampilan item
        holder.binding.ivItemFamili.setImageResource(famili.icon)
        holder.binding.tvItemFamili.text = famili.name
        // Menetapkan listener onClick untuk item
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listFamili[holder.adapterPosition])
        }
    }

    // Override metode getItemCount untuk mengembalikan jumlah item dalam daftar
    override fun getItemCount(): Int = listFamili.size

    // Mendefinisikan kelas inner ViewHolder
    class ListViewHolder(var binding: ItemRowFamiliBinding) :
        RecyclerView.ViewHolder(binding.root)

    // Interface untuk menangani klik item dalam RecyclerView
    interface OnItemClickCallback {
        fun onItemClicked(data: Famili)
    }
}