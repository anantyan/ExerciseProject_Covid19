package id.anantyan.covid19.ui.score

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.anantyan.covid19.common.Const
import id.anantyan.covid19.common.Resource
import id.anantyan.covid19.common.dividerVertical
import id.anantyan.covid19.databinding.ActivityScoreBinding
import javax.inject.Inject

@AndroidEntryPoint
class ScoreActivity : AppCompatActivity() {

    private lateinit var binding: ActivityScoreBinding
    private val viewModel: ScoreViewModel by viewModels()

    @Inject lateinit var adapter: ScoreAdapterHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScoreBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        if (intent.hasExtra(Const.PASSING_TO_SCORE)) {
            val data = intent.getStringExtra(Const.PASSING_TO_SCORE).toString()
            supportActionBar?.title = data
            viewModel.getScore(data)
        }

        binding.rvScore.setHasFixedSize(true)
        binding.rvScore.layoutManager = LinearLayoutManager(this)
        binding.rvScore.itemAnimator = DefaultItemAnimator()
        binding.rvScore.isNestedScrollingEnabled = true
        binding.rvScore.addItemDecoration(dividerVertical(this, 32, 32))

        viewModel.responseGetSkor.observe(this) {
            when(it) {
                is Resource.Loading -> {
                    Toast.makeText(this, "Loading...", Toast.LENGTH_SHORT).show()
                }
                is Resource.Success -> {
                    adapter.differ(it.data!!)
                    binding.rvScore.adapter = adapter.init()
                }
                is Resource.Error -> {
                    Log.d("COVID-API", "${it.message}")
                    Toast.makeText(this, "${it.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}