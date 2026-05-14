package com.example.pashuaahar

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class RecipeActivity : AppCompatActivity() {

    private lateinit var txtRecipeResult: TextView
    private lateinit var btnGenerateRecipe: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe)

        txtRecipeResult = findViewById(R.id.txtRecipeResult)
        btnGenerateRecipe = findViewById(R.id.btnGenerateRecipe)

        // Get data from SharedPreferences (saved from CowProfile)
        val sharedPreferences = getSharedPreferences(MainActivity.PREF_NAME, MODE_PRIVATE)

        val breed = intent.getStringExtra("breed")
            ?: sharedPreferences.getString(MainActivity.KEY_BREED, "HF") ?: "HF"
        val age = intent.getStringExtra("age")
            ?: sharedPreferences.getString(MainActivity.KEY_AGE, "3") ?: "3"
        val weight = intent.getStringExtra("weight")
            ?: sharedPreferences.getString(MainActivity.KEY_WEIGHT, "400") ?: "400"
        val milkYield = intent.getStringExtra("milkYield")
            ?: sharedPreferences.getString(MainActivity.KEY_MILK_YIELD, "10") ?: "10"

        btnGenerateRecipe.setOnClickListener {
            generateRecipe(breed, age, weight, milkYield)
        }

        // Auto-generate on open
        generateRecipe(breed, age, weight, milkYield)
    }

    private fun generateRecipe(breed: String, age: String, weight: String, milkYield: String) {
        // Simple recipe calculation based on milk yield
        val milkYieldValue = milkYield.toDoubleOrNull() ?: 10.0

        val maizeKg = (milkYieldValue * 0.8).toString()
        val cottonseedKg = (milkYieldValue * 0.5).toString()
        val proteinGrams = (milkYieldValue * 150).toString()
        val energyMJ = (milkYieldValue * 75).toString()
        val dailyCost = (milkYieldValue * 50).toString()

        val recipe = """
            ===== FEED RECIPE FOR $breed =====
            
            COW DETAILS:
            Breed: $breed
            Age: $age years
            Weight: $weight kg
            Milk Yield Target: $milkYield liters/day
            
            ===== DAILY FEED MIX =====
            - Maize: $maizeKg kg
            - Cottonseed Cake: $cottonseedKg kg
            - Mineral Mix: 0.5 kg
            - Salt: 0.05 kg
            
            ===== NUTRITIONAL INFO =====
            - Total Protein: $proteinGrams grams
            - Total Energy: $energyMJ MJ
            
            ===== COST ESTIMATE =====
            - Daily Cost: Rs. $dailyCost
            - Monthly Cost: Rs. ${(dailyCost.toDouble() * 30).toInt()}
            
            ===== FEEDING TIPS =====
            1. Feed in two equal portions (morning & evening)
            2. Provide clean, fresh water at all times
            3. Mix the feed well before feeding
            4. Store feed in a dry place away from moisture
            5. Adjust portions if milk yield increases or decreases
            
            ===== HEALTH NOTES =====
            - Ensure regular veterinary checkups
            - Vaccinate against common diseases
            - Maintain proper hygiene in the shed
            - Provide adequate rest (8-10 hours/day)
        """.trimIndent()

        txtRecipeResult.text = recipe
    }
}