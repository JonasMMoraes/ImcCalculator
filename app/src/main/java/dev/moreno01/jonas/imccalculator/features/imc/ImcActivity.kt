package dev.moreno01.jonas.imccalculator.features.imc

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import dev.moreno01.jonas.imccalculator.R
import dev.moreno01.jonas.imccalculator.databinding.ActivityImcBinding

class ImcActivity : AppCompatActivity() {

    private lateinit var binding: ActivityImcBinding
    private lateinit var viewModel: ImcViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityImcBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[ImcViewModel::class.java]

        createObservers()
        setListeners()
    }

    private fun createObservers() {
        viewModel.imc.observe(this) { imc ->
            imc.let {
                binding.tvResult.text = getString(R.string.imc_result, String.format("%.2f", it))
            }
        }
    }

    private fun setListeners() {
        binding.btCalculate.setOnClickListener {

            val rawHeight = binding.edtHeight.text.toString().replace(',', '.')
            val heightInMeters = if (rawHeight.contains(".")) {
                rawHeight.toDouble()
            } else {
                rawHeight.toDouble() / 100
            }

            val rawWeight = binding.edtWeight.text.toString().replace(',', '.')
            val weight = rawWeight.toDouble()

            viewModel.calculateImc(heightInMeters, weight)
        }
    }
}