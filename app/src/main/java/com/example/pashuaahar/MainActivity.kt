package com.example.pashuaahar

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences = getSharedPreferences("CowData", MODE_PRIVATE)

        // Find buttons
        val btnProfile = findViewById<LinearLayout>(R.id.btnProfile)
        val btnRecipe = findViewById<LinearLayout>(R.id.btnRecipe)
        val btnCost = findViewById<LinearLayout>(R.id.btnCost)
        val btnTips = findViewById<LinearLayout>(R.id.btnTips)

        // Set click listeners
        if (btnProfile != null) {
            btnProfile.setOnClickListener {
                startActivity(Intent(this, CowProfileActivity::class.java))
            }
        }

        if (btnRecipe != null) {
            btnRecipe.setOnClickListener {
                startActivity(Intent(this, RecipeActivity::class.java))
            }
        }

        if (btnCost != null) {
            btnCost.setOnClickListener {
                startActivity(Intent(this, CostTrackerActivity::class.java))
            }
        }

        if (btnTips != null) {
            btnTips.setOnClickListener {
                startActivity(Intent(this, VetTipsActivity::class.java))
            }
        }
    }

    companion object {
        const val PREF_NAME = "CowData"
        const val KEY_BREED = "breed"
        const val KEY_AGE = "age"
        const val KEY_WEIGHT = "weight"
        const val KEY_MILK_YIELD = "milkYield"
    }
}