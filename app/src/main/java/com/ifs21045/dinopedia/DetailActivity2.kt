package com.ifs21045.dinopedia

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.ifs21045.dinopedia.databinding.ActivityDetail2Binding

class DetailActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityDetail2Binding
    private var tyrannosaurus: Tyrannosaurus? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetail2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // Mendapatkan objek Tyrannosaurus dari intent
        tyrannosaurus = intent.getParcelableExtra(EXTRA_TYRANNOSAURUS)

        // Mengatur judul ActionBar
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title =
                "Detail ${tyrannosaurus?.name ?: ""}" // Menggunakan Elvis operator untuk menghindari null pointer
        }

        // Memuat data Tyrannosaurus ke tampilan
        tyrannosaurus?.let { loadData(it) }
    }

    // Fungsi untuk memuat data Tyrannosaurus ke tampilan
    private fun loadData(tyrannosaurus: Tyrannosaurus) {
        binding.ivDetailIconTyrannosaurus.setImageResource(tyrannosaurus.icon)
        binding.tvDetailNameTyrannosaurus.text = tyrannosaurus.name
        binding.tvDetailDeskripsiTyrannosaurus.text = tyrannosaurus.deskripsi
        binding.tvDetailKarakteristikTyrannosaurus.text = tyrannosaurus.karakteristik
        binding.tvDetailKelompokTyrannosaurus.text = tyrannosaurus.kelompok // Ini sudah benar
        binding.tvDetailHabitatTyrannosaurus.text = tyrannosaurus.habitat
        binding.tvDetailMakananTyrannosaurus.text = tyrannosaurus.makanan
        binding.tvDetailPanjangTyrannosaurus.text = tyrannosaurus.panjang
        binding.tvDetailTinggiTyrannosaurus.text = tyrannosaurus.tinggi
        binding.tvDetailBobotTyrannosaurus.text = tyrannosaurus.bobot
    }

    // Mengatasi aksi saat tombol kembali di ActionBar ditekan
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        const val EXTRA_TYRANNOSAURUS = "extra_tyrannosaurus"
    }
}
