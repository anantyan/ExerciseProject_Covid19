package id.anantyan.covid19.ui.province

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.chip.Chip
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import id.anantyan.covid19.common.Const
import id.anantyan.covid19.common.Resource
import id.anantyan.covid19.common.dividerVertical
import id.anantyan.covid19.databinding.ActivityMainBinding
import id.anantyan.covid19.databinding.ChipOnDialogBinding
import id.anantyan.covid19.databinding.DialogSetMainBinding
import id.anantyan.covid19.model.ListDataItem
import id.anantyan.covid19.ui.score.ScoreActivity
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    @Inject lateinit var adapter: MainAdapterHelper

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvCovid.setHasFixedSize(true)
        binding.rvCovid.layoutManager = LinearLayoutManager(this)
        binding.rvCovid.itemAnimator = DefaultItemAnimator()
        binding.rvCovid.isNestedScrollingEnabled = true
        binding.rvCovid.addItemDecoration(dividerVertical(this, 32, 32))

        viewModel.getCovid()
        viewModel.responseGetCovid.observe(this) {
            when(it) {
                is Resource.Loading -> {
                    Toast.makeText(this, "Loading...", Toast.LENGTH_SHORT).show()
                }
                is Resource.Success -> {
                    adapter.differ(it.data?.listData!!)
                    binding.txtDate.text = "Update terakhir : ${it.data.lastDate}"
                    binding.rvCovid.adapter = adapter.init()
                }
                is Resource.Error -> {
                    Log.d("COVID-API", "${it.message}")
                    Toast.makeText(this, "${it.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }

        adapter.onClick { _, item ->
            dialog(item)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun dialog(item: ListDataItem) {
        val builder = MaterialAlertDialogBuilder(this)
        val binding = DialogSetMainBinding.inflate(LayoutInflater.from(this))
        builder.setCancelable(false)
        builder.setTitle(item.key)
        builder.setView(binding.root)
        binding.jmlKasus.text = item.jumlahKasus.toString()
        binding.jmlSembuh.text = item.jumlahSembuh.toString()
        binding.jmlMeninggal.text = item.jumlahMeninggal.toString()
        binding.jmlDirawat.text = item.jumlahDirawat.toString()
        item.jenisKelamin?.forEach {
            binding.chipJenisKelamin.addView(chip("${it.key} : ${it.docCount}"))
        }
        item.kelompokUmur?.forEach {
            binding.chipUmur.addView(chip("${it.key} : ${it.docCount}"))
        }
        builder.setPositiveButton("Oke") { dialog, _ ->
            dialog.dismiss()
        }
        builder.setNeutralButton("Detail") { _, _ ->
            val data = item.key
            val intent = Intent(this, ScoreActivity::class.java)
            intent.putExtra(Const.PASSING_TO_SCORE, data)
            startActivity(intent)
        }
        builder.show()
    }

    private fun chip(title: String): Chip {
        val chip = ChipOnDialogBinding.inflate(LayoutInflater.from(this)).root
        chip.text = title
        return chip
    }
}