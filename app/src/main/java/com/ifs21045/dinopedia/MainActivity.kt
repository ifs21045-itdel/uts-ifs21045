// Mendefinisikan paket dari kelas ini
package com.ifs21045.dinopedia

// Mengimpor kelas-kelas yang diperlukan dari library Android
import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ifs21045.dinopedia.databinding.ActivityMainBinding

// Mendefinisikan kelas MainActivity yang merupakan turunan dari AppCompatActivity
class MainActivity : AppCompatActivity() {

    // Mendeklarasikan variabel lateinit untuk binding layout ActivityMainBinding
    private lateinit var binding: ActivityMainBinding

    // Mendeklarasikan variabel dataFamili sebagai ArrayList dari objek Famili
    private val dataFamili = ArrayList<Famili>()

    // Metode onCreate yang dipanggil saat aktivitas dibuat
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Menginisialisasi binding dengan menggunakan layout inflater
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Mengatur bahwa RecyclerView tidak memiliki ukuran yang tetap
        binding.rvFamili.setHasFixedSize(false)
        // Menambahkan semua data famili ke dalam dataFamili
        dataFamili.addAll(getListFamili())
        // Menampilkan RecyclerView dengan daftar famili
        showRecyclerList()
    }

    // Override metode onCreateOptionsMenu untuk membuat menu pada ActionBar
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    // Override metode onOptionsItemSelected untuk menangani item menu yang dipilih
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_profile -> {
                // Membuat intent untuk menavigasi ke ProfileActivity
                val intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)
                true
            }
            // Menangani item menu lainnya
            else -> super.onOptionsItemSelected(item)
        }
    }

    // Metode untuk mendapatkan daftar famili dari sumber daya string
    @SuppressLint("Recycle")
    private fun getListFamili(): ArrayList<Famili> {
        // Mendapatkan data famili dari sumber daya string
        val dataName = resources.getStringArray(R.array.cindy_simangunsong_famili_name)
        val dataIcon = resources.obtainTypedArray(R.array.cindy_simangunsong_famili_icon)
        val dataDescription = resources.getStringArray(R.array.cindy_simangunsong_famili_description)
        val dataPeriode = resources.getStringArray(R.array.cindy_simangunsong_famili_periode)
        val dataKarakteristik = resources.getStringArray(R.array.cindy_simangunsong_famili_karakteristik)
        val dataHabitat = resources.getStringArray(R.array.cindy_simangunsong_famili_habitat)
        val dataKlasifikasi = resources.getStringArray(R.array.cindy_simangunsong_famili_klasifikasi)
        val startIndex = resources.getStringArray(R.array.start_index_dino)
        val endIndex = resources.getStringArray(R.array.end_index_dino)

        // Membuat dan mengisi listFamili dengan objek-objek Famili
        val listFamili = ArrayList<Famili>()
        for (i in dataName.indices) {
            val famili = Famili(dataName[i],
                dataIcon.getResourceId(i, -1), dataDescription[i],
                dataPeriode[i], dataKarakteristik[i], dataHabitat[i], dataKlasifikasi[i], startIndex[i].toInt(), endIndex[i].toInt())
            listFamili.add(famili)
        }
        return listFamili
    }

    // Metode untuk menampilkan RecyclerView dengan daftar famili
    private fun showRecyclerList() {
        // Mengatur layout manager RecyclerView sesuai dengan orientasi layar
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            binding.rvFamili.layoutManager = GridLayoutManager(this, 2)
        } else {
            binding.rvFamili.layoutManager = LinearLayoutManager(this)
        }

        // Membuat adapter untuk RecyclerView
        val listFamiliAdapter = ListFamiliAdapter(dataFamili)
        // Menetapkan adapter ke RecyclerView
        binding.rvFamili.adapter = listFamiliAdapter

        // Menetapkan callback onClick untuk adapter
        listFamiliAdapter.setOnItemClickCallback(object :
            ListFamiliAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Famili) {
                // Menampilkan detail famili yang dipilih
                showSelectedFamili(data)
            }
        })
    }

    // Metode untuk menampilkan aktivitas DetailActivity dengan data famili yang dipilih
    private fun showSelectedFamili(famili: Famili) {
        val intentWithData = Intent(this@MainActivity,
            DetailActivity::class.java)
        intentWithData.putExtra(DetailActivity.EXTRA_FAMILI, famili)
        startActivity(intentWithData)
    }
}
