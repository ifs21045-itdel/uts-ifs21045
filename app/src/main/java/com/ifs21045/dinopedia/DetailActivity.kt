package com.ifs21045.dinopedia
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.ifs21045.dinopedia.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private var famili: Famili? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        famili = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_FAMILI,
                Famili::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_FAMILI)
        }
        //Mengatur action bar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        if (famili != null) {
            supportActionBar?.title = "Dino ${famili!!.name}"
            loadData(famili!!)
        } else {
            finish()
        }

        //code untuk ketika di klik akan menampilkan detail dino
        binding.btnExample.setOnClickListener{
            val intentWithData = Intent(this@DetailActivity, DinoActivity::class.java)
            intentWithData.putExtra(DinoActivity.EXTRA_FAMILI, famili!!)
            startActivity(intentWithData)
        }
//        val btnExample: Button = findViewById(R.id.btnExample)
//        btnExample.setOnClickListener {
//            val intent = Intent(this@DetailActivity, DinoActivity::class.java).apply {
//                putExtra(DinoActivity.EXTRA_FAMILI, famili)
//            }
//            startActivity(intent)
//        }
    }
    private fun loadData(famili: Famili) {
        binding.ivDetailIcon.setImageResource(famili.icon)
        binding.tvDetailName.text = famili.name
        binding.tvDetailDeskripsi.text = famili.description
        binding.tvDetailPeriode.text = famili.periode
        binding.tvDetailKarakteristik.text = famili.karakteristik
        binding.tvDetailHabitat.text = famili.habitat
        binding.tvDetailKlasifikasi.text = famili.klasifikasi
    }
    //Ini adalah kasus ketika item yang dipilih adalah tombol kembali (home) di ActionBar.
    // Jika pengguna memilih tombol kembali, maka aktivitas akan ditutup (finish()).
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
   // kode ini mendefinisikan sebuah companion object di dalam kelas yang mengandung sebuah konstanta bernama
   // EXTRA_FAMILI yang digunakan mungkin untuk menyimpan kunci ekstra yang akan digunakan dalam pertukaran data
   // antar komponen aplikasi, seperti dalam penggunaan Intent untuk melampirkan data tambahan
    companion object {
        const val EXTRA_FAMILI = "extra_famili"
    }
}