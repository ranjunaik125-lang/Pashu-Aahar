package com.example.pashuaahar

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CowProfileActivity : AppCompatActivity() {

    private lateinit var spinnerBreed: Spinner
    private lateinit var etAge: EditText
    private lateinit var etWeight: EditText
    private lateinit var etMilkYield: EditText
    private lateinit var btnCalculate: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cow_profile)

        // Initialize views
        spinnerBreed = findViewById(R.id.spinnerBreed)
        etAge = findViewById(R.id.etAge)
        etWeight = findViewById(R.id.etWeight)
        etMilkYield = findViewById(R.id.etMilkYield)
        btnCalculate = findViewById(R.id.btnCalculate)

        // Setup breed spinner
        val breeds = arrayOf("Jersey", "HF", "Desi", "Sahiwal")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, breeds)
        spinnerBreed.adapter = adapter

        // Calculate button click listener
        btnCalculate.setOnClickListener {
            validateAndCalculate()
        }
    }

    private fun validateAndCalculate() {
        val breed = spinnerBreed.selectedItem.toString()
        val age = etAge.text.toString()
        val weight = etWeight.text.toString()
        val milkYield = etMilkYield.text.toString()

        // Validate inputs
        if (age.isEmpty() || weight.isEmpty() || milkYield.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        }

        // Save to SharedPreferences
        val sharedPreferences = getSharedPreferences(MainActivity.PREF_NAME, MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(MainActivity.KEY_BREED, breed)
        editor.putString(MainActivity.KEY_AGE, age)
        editor.putString(MainActivity.KEY_WEIGHT, weight)
        editor.putString(MainActivity.KEY_MILK_YIELD, milkYield)
        editor.apply()

        // Navigate to Recipe with data
        val intent = Intent(this, RecipeActivity::class.java)
        intent.putExtra("breed", breed)
        intent.putExtra("age", age)
        intent.putExtra("weight", weight)
        intent.putExtra("milkYield", milkYield)
        startActivity(intent)

        Toast.makeText(this, "Data saved!", Toast.LENGTH_SHORT).show()
    }
}