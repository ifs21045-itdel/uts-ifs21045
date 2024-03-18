package com.ifs21045.dinopedia

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

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val dataFamili = ArrayList<Famili>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rvFamili.setHasFixedSize(false)
        dataFamili.addAll(getListFamili())
        showRecyclerList()
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_profile -> {
                val intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
    @SuppressLint("Recycle")
    private fun getListFamili(): ArrayList<Famili> {
        val dataName =
            resources.getStringArray(R.array.famili_name)
        val dataIcon =
            resources.obtainTypedArray(R.array.famili_icon)
        val dataDescription =
            resources.getStringArray(R.array.famili_description)
        val dataPeriode =
            resources.getStringArray(R.array.famili_periode)
        val dataKarakteristik =
            resources.getStringArray(R.array.famili_karakteristik)
        val dataHabitat =
            resources.getStringArray(R.array.famili_habitat)
        val dataKlasifikasi =
            resources.getStringArray(R.array.famili_klasifikasi)
        val listFamili = ArrayList<Famili>()
        for (i in dataName.indices) {
            val famili = Famili(dataName[i],
                dataIcon.getResourceId(i, -1), dataDescription[i],
                dataPeriode[i], dataKarakteristik[i], dataHabitat[i], dataKlasifikasi[i])
            listFamili.add(famili)
        }
        return listFamili
    }
    private fun showRecyclerList() {
        if (resources.configuration.orientation ==
            Configuration.ORIENTATION_LANDSCAPE) {
            binding.rvFamili.layoutManager =
                GridLayoutManager(this, 2)
        } else {
            binding.rvFamili.layoutManager =
                LinearLayoutManager(this)
        }
        val listFamiliAdapter = ListFamiliAdapter(dataFamili)
        binding.rvFamili.adapter = listFamiliAdapter
        listFamiliAdapter.setOnItemClickCallback(object :
            ListFamiliAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Famili) {
                showSelectedFamili(data)
            }
        })
    }
    private fun showSelectedFamili(famili: Famili) {
        val intentWithData = Intent(this@MainActivity,
            DetailActivity::class.java)
        intentWithData.putExtra(DetailActivity.EXTRA_FAMILI, famili)
        startActivity(intentWithData)
    }
}
