package com.example.conversordeunidades

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var spinnerFrom: Spinner
    private lateinit var spinnerTo: Spinner
    private lateinit var editValue: EditText
    private lateinit var buttonConvert: Button
    private lateinit var textResult: TextView

    private val units = arrayOf("Centímetros", "Metros", "Quilômetros", "Milhas")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        spinnerFrom = findViewById(R.id.spinnerFrom)
        spinnerTo = findViewById(R.id.spinnerTo)
        editValue = findViewById(R.id.editValue)
        buttonConvert = findViewById(R.id.buttonConvert)
        textResult = findViewById(R.id.textResult)

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, units)
        spinnerFrom.adapter = adapter
        spinnerTo.adapter = adapter

        buttonConvert.setOnClickListener {
            val input = editValue.text.toString()
            if (input.isEmpty()) {
                Toast.makeText(this, "Digite um valor", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val value = input.toDouble()
            val from = spinnerFrom.selectedItem.toString()
            val to = spinnerTo.selectedItem.toString()

            val result = convert(value, from, to)
            textResult.text = "Resultado: $result $to"
        }
    }

    private fun convert(value: Double, from: String, to: String): Double {
        val valueInMeters = when (from) {
            "Centímetros" -> value / 100
            "Metros" -> value
            "Quilômetros" -> value * 1000
            "Milhas" -> value * 1609.34
            else -> value
        }

        return when (to) {
            "Centímetros" -> valueInMeters * 100
            "Metros" -> valueInMeters
            "Quilômetros" -> valueInMeters / 1000
            "Milhas" -> valueInMeters / 1609.34
            else -> valueInMeters
        }
    }
}
