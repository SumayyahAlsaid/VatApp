package com.example.vatapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.vatapp.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    // binding object
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root // reference layout file
        setContentView(view) // link layout

        binding.btnCalculateVat.setOnClickListener {
            calculateVat()
        }// End setOnClickListener
    }

    private fun calculateVat() {

        val stringInTextField = binding.etTotalCost.text.toString()
        val cost = stringInTextField.toDouble()

        val vatPercentage = when(binding.rgVatOption.checkedRadioButtonId) {
            R.id.rb_vat_10 -> 0.10
            R.id.rb_vat_15 -> 0.15
            else -> 0.20
        }

        var vat = vatPercentage * cost
        var total = vat + cost

        // check switch
        val roundVat = binding.switchRoundUp.isChecked
        if (roundVat){
            total = kotlin.math.ceil(total)
        }

        // Total Formatting
        val formattedTotal = NumberFormat.getCurrencyInstance().format(total)
        binding.txtCostTotal.text = getString(R.string.total_amount, formattedTotal)
    }

}

