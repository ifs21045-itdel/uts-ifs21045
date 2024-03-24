package com.ifs21045.dinopedia
import android.content.Intent
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager

import com.ifs21045.dinopedia.databinding.ActivityDinoBinding

class DinoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDinoBinding
    private val dataTyrannosaurus = ArrayList<Tyrannosaurus>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDinoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rvTyrannosaurus.setHasFixedSize(false)
        dataTyrannosaurus.addAll(getListTyrannosaurus())
        showRecyclerList()
    }

    private fun getListTyrannosaurus(): ArrayList<Tyrannosaurus> {
        val famili=if (Build.VERSION.SDK_INT >= 33){
            intent.getParcelableExtra(
                EXTRA_FAMILI,
                Famili::class.java)
        }else{
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_FAMILI)
        }
        val dataName =
            resources.getStringArray(R.array.cindy_simangunsong_dino_name)
        val dataIcon =
            resources.obtainTypedArray(R.array.dino_icon)
        val dataDescription =
            resources.getStringArray(R.array.dino_description)
        val dataKarakteristik =
            resources.getStringArray(R.array.dino_karakteristik)
        val dataKelompok =
            resources.getStringArray(R.array.dino_kelompok)
        val dataHabitat =
            resources.getStringArray(R.array.dino_habitat)
        val dataMakanan =
            resources.getStringArray(R.array.dino_makanan)
        val dataPanjang =
            resources.getStringArray(R.array.dino_panjang)
        val dataTinggi =
            resources.getStringArray(R.array.dino_tinggi)
        val dataBobot =
            resources.getStringArray(R.array.dino_bobot)
        val dataKelemahan =
            resources.getStringArray(R.array.dino_kelemahan)
        val startIndex = famili?.startIndex
        val endIndex = famili?.endIndex
        val listTyrannosaurus = ArrayList<Tyrannosaurus>()
        for (i in startIndex!!..endIndex!!) {
            val tyrannosaurus = Tyrannosaurus(
                name = dataName[i],
                icon = dataIcon.getResourceId(i, -1),
                deskripsi = dataDescription[i],
                karakteristik = dataKarakteristik[i],
                kelompok = dataKelompok[i],
                habitat = dataHabitat[i],
                makanan = dataMakanan[i],
                panjang = dataPanjang[i],
                tinggi = dataTinggi[i],
                bobot = dataBobot[i],
                kelemahan = dataKelemahan[i]
            )
            listTyrannosaurus.add(tyrannosaurus)
        }
        // Setelah loop, pastikan untuk melepaskan array yang telah diperoleh
        dataIcon.recycle()
        return listTyrannosaurus
    }

    private fun showRecyclerList() {
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            binding.rvTyrannosaurus.layoutManager = GridLayoutManager(this, 2)
        } else {
            binding.rvTyrannosaurus.layoutManager = LinearLayoutManager(this)
        }
        val listTyrannosaurusAdapter = ListTyrannosaurusAdapter(dataTyrannosaurus)
        binding.rvTyrannosaurus.adapter = listTyrannosaurusAdapter
        listTyrannosaurusAdapter.setOnItemClickCallback(object :
            ListTyrannosaurusAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Tyrannosaurus) {
                showSelectedTyrannosaurus(data)
            }
        })
    }

    private fun showSelectedTyrannosaurus(tyrannosaurus: com.ifs21045.dinopedia.Tyrannosaurus) {
        val intent = Intent(this@DinoActivity, DetailActivity2::class.java)
        intent.putExtra(DetailActivity2.EXTRA_TYRANNOSAURUS, tyrannosaurus)
        startActivity(intent)
    }
    companion object {
        const val EXTRA_FAMILI = "extra_famili"
    }

}
