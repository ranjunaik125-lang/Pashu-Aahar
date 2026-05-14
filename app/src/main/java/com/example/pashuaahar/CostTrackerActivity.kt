package com.example.pashuaahar

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CostTrackerActivity : AppCompatActivity() {

    private lateinit var txtHomemadeCost: TextView
    private lateinit var txtMarketCost: TextView
    private lateinit var txtDailySavings: TextView
    private lateinit var txtMonthlySavings: TextView
    private lateinit var txtYearlySavings: TextView
    private lateinit var txtBreakdown: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cost_tracker)

        // Initialize views
        txtHomemadeCost = findViewById(R.id.txtHomemadeCost)
        txtMarketCost = findViewById(R.id.txtMarketCost)
        txtDailySavings = findViewById(R.id.txtDailySavings)
        txtMonthlySavings = findViewById(R.id.txtMonthlySavings)
        txtYearlySavings = findViewById(R.id.txtYearlySavings)
        txtBreakdown = findViewById(R.id.txtBreakdown)

        // Get data from SharedPreferences
        val sharedPreferences = getSharedPreferences(MainActivity.PREF_NAME, MODE_PRIVATE)

        val breed = sharedPreferences.getString(MainActivity.KEY_BREED, "HF") ?: "HF"
        val milkYield = sharedPreferences.getString(MainActivity.KEY_MILK_YIELD, "10")?.toDoubleOrNull() ?: 10.0

        // Calculate costs
        calculateCosts(milkYield, breed)
    }

    private fun calculateCosts(milkYield: Double, breed: String) {
        // Home-made feed cost calculation (from RecipeActivity formula)
        val maizeKg = milkYield * 0.8
        val cottonseedKg = milkYield * 0.5

        // Cost per kg
        val maizeCostPerKg = 25.0
        val cottonseedCostPerKg = 50.0
        val mineralCostPerKg = 60.0
        val saltCostPerKg = 400.0

        // Daily costs
        val maizeCost = (maizeKg * maizeCostPerKg).toInt()
        val cottonseedCost = (cottonseedKg * cottonseedCostPerKg).toInt()
        val mineralCost = (0.5 * mineralCostPerKg).toInt()
        val saltCost = (0.05 * saltCostPerKg).toInt()

        val dailyHomemadeCost = maizeCost + cottonseedCost + mineralCost + saltCost

        // Market feed cost (typically 30-40% more expensive)
        val dailyMarketCost = (dailyHomemadeCost * 1.35).toInt()

        // Savings
        val dailySavings = dailyMarketCost - dailyHomemadeCost
        val monthlySavings = dailySavings * 30
        val yearlySavings = dailySavings * 365

        // Update UI
        txtHomemadeCost.text = "Daily: Rs. $dailyHomemadeCost\nMonthly: Rs. ${dailyHomemadeCost * 30}"
        txtMarketCost.text = "Daily: Rs. $dailyMarketCost\nMonthly: Rs. ${dailyMarketCost * 30}"
        txtDailySavings.text = "Daily Savings: Rs. $dailySavings"
        txtMonthlySavings.text = "Monthly Savings: Rs. $monthlySavings"
        txtYearlySavings.text = "Yearly Savings: Rs. $yearlySavings"

        // Cost breakdown
        val breakdown = """
            Maize: Rs. $maizeCost/day
            Cottonseed Cake: Rs. $cottonseedCost/day
            Mineral Mix: Rs. $mineralCost/day
            Salt: Rs. $saltCost/day
            ─────────────────────
            Total: Rs. $dailyHomemadeCost/day
        """.trimIndent()

        txtBreakdown.text = breakdown
    }
}