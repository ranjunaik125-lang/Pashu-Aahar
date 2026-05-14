package com.example.pashuaahar

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class VetTipsActivity : AppCompatActivity() {

    private val TAG = "VetTipsActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vet_tips)

        Log.d(TAG, "Activity created, loading layout")

        try {
            val tipsContainer = findViewById<LinearLayout>(R.id.tipsContainer)
            Log.d(TAG, "tipsContainer found: ${tipsContainer != null}")

            if (tipsContainer == null) {
                Log.e(TAG, "ERROR: tipsContainer is NULL! Check activity_vet_tips.xml")
                Toast.makeText(this, "Layout error: tipsContainer not found", Toast.LENGTH_LONG).show()
                return
            }

            val btnHygiene = findViewById<Button>(R.id.btnHygiene)
            val btnFodder = findViewById<Button>(R.id.btnFodder)
            val btnDisease = findViewById<Button>(R.id.btnDisease)
            val btnMilking = findViewById<Button>(R.id.btnMilking)
            val btnBack = findViewById<ImageButton>(R.id.btnBack)

            Log.d(TAG, "All views found successfully")

            btnBack?.setOnClickListener { finish() }
            btnHygiene?.setOnClickListener { displayTips(tipsContainer, "Hygiene") }
            btnFodder?.setOnClickListener { displayTips(tipsContainer, "Fodder") }
            btnDisease?.setOnClickListener { displayTips(tipsContainer, "Disease") }
            btnMilking?.setOnClickListener { displayTips(tipsContainer, "Milking") }

            Log.d(TAG, "Loading default Hygiene tips")
            displayTips(tipsContainer, "Hygiene")

        } catch (e: Exception) {
            Log.e(TAG, "Exception: ${e.message}", e)
            Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }

    private val videoLinks = mapOf(
        "Hygiene" to listOf(
            "Cattle Shed Hygiene" to "https://youtu.be/TIcyn36LPGs",
            "Dairy Cattle Hygiene" to "https://youtu.be/TIcyn36LPGs",
            "Udder Cleaning" to "https://youtu.be/TIcyn36LPGs",
            "Shed Cleaning" to "https://youtu.be/TIcyn36LPGs",
            "Disease Prevention" to "https://youtu.be/TIcyn36LPGs"
        ),
        "Fodder" to listOf(
            "Green Fodder Storage" to "https://youtu.be/TIcyn36LPGs",
            "Dry Fodder" to "https://youtu.be/TIcyn36LPGs",
            "Fodder Quality" to "https://youtu.be/TIcyn36LPGs",
            "Silage Making" to "https://youtu.be/TIcyn36LPGs",
            "Mold Prevention" to "https://youtu.be/TIcyn36LPGs"
        ),
        "Disease" to listOf(
            "Common Diseases" to "https://youtu.be/TIcyn36LPGs",
            "Mastitis" to "https://youtu.be/TIcyn36LPGs",
            "Foot & Mouth" to "https://youtu.be/TIcyn36LPGs",
            "Symptoms" to "https://youtu.be/TIcyn36LPGs",
            "Home Remedies" to "https://youtu.be/TIcyn36LPGs"
        ),
        "Milking" to listOf(
            "Hand Milking" to "https://youtu.be/TIcyn36LPGs",
            "Machine Milking" to "https://youtu.be/TIcyn36LPGs",
            "Hygiene" to "https://youtu.be/TIcyn36LPGs",
            "Quality" to "https://youtu.be/TIcyn36LPGs",
            "Yield" to "https://youtu.be/TIcyn36LPGs"
        )
    )

    private val tipCategories = mapOf(
        "Hygiene" to listOf(
            "• Clean cow shed daily to prevent diseases",
            "• Provide clean drinking water 3-4 times daily",
            "• Keep fodder in dry place away from moisture",
            "• Wash cow's udder before milking",
            "• Use separate cloths for each cow during milking"
        ),
        "Fodder" to listOf(
            "• Store dry fodder in cool, ventilated area",
            "• Check for mold before feeding hay",
            "• Mix green and dry fodder for balanced diet",
            "• Give 2kg maize + 1kg cake for good milk yield",
            "• Ensure mineral mix in daily feed"
        ),
        "Disease" to listOf(
            "• Foot & Mouth: Isolate cow, apply salt+turmeric on hooves",
            "• Mastitis: Massage udder, use warm water, consult vet",
            "• Bloating: Give salt solution, reduce grain feed",
            "• Diarrhea: Provide rice water, reduce green fodder",
            "• Fever: Check temperature, give cool water, rest"
        ),
        "Milking" to listOf(
            "• Milk at same time daily for more yield",
            "• Use clean hands and sterilized equipment",
            "• Don't strip milk (harms udder tissue)",
            "• Jersey cows give 10-15L per day, Desi give 4-6L",
            "• Milk 2-3 hours after calf separation"
        )
    )

    private fun displayTips(container: LinearLayout, category: String) {
        Log.d(TAG, "Displaying tips for: $category")
        container.removeAllViews()

        val tips = tipCategories[category] ?: listOf()
        val videos = videoLinks[category] ?: listOf()

        // Show tips
        for (tip in tips) {
            val card = LinearLayout(this)
            card.orientation = LinearLayout.VERTICAL
            card.setPadding(32, 24, 32, 24)
            card.setBackgroundResource(android.R.drawable.dialog_holo_light_frame)
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(0, 0, 0, 16)
            card.layoutParams = params

            val tv = TextView(this)
            tv.text = tip
            tv.textSize = 13f
            tv.setTextColor(android.graphics.Color.parseColor("#333333"))

            card.addView(tv)
            container.addView(card)
        }

        // Show videos
        if (videos.isNotEmpty()) {
            val header = LinearLayout(this)
            header.orientation = LinearLayout.VERTICAL
            header.setPadding(32, 24, 32, 24)
            header.setBackgroundResource(android.R.drawable.dialog_holo_light_frame)
            val hParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            hParams.setMargins(0, 16, 0, 8)
            header.layoutParams = hParams

            val tvHeader = TextView(this)
            tvHeader.text = "🎬 Video Resources"
            tvHeader.textSize = 15f
            tvHeader.setTypeface(null, android.graphics.Typeface.BOLD)
            tvHeader.setTextColor(android.graphics.Color.parseColor("#E65100"))

            header.addView(tvHeader)
            container.addView(header)

            for ((title, url) in videos) {
                val btn = Button(this)
                btn.text = "▶️ $title"
                btn.setBackgroundColor(android.graphics.Color.parseColor("#E65100"))
                btn.setTextColor(android.graphics.Color.WHITE)
                val bParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                bParams.setMargins(0, 8, 0, 8)
                btn.layoutParams = bParams
                btn.textSize = 12f

                btn.setOnClickListener {
                    Log.d(TAG, "Opening video: $url")
                    openVideo(url)
                }

                container.addView(btn)
            }
        }
    }

    private fun openVideo(url: String) {
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        } catch (e: Exception) {
            Log.e(TAG, "Video error: ${e.message}")
            Toast.makeText(this, "Cannot open video", Toast.LENGTH_SHORT).show()
        }
    }
}