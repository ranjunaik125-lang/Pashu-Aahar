package com.example.pashuaahar.ui.cost

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.pashuaahar.R

class CostFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cost, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvHomeCost = view.findViewById<TextView>(R.id.tvHomeCost)
        val tvMarketCost = view.findViewById<TextView>(R.id.tvMarketCost)
        val tvSavings = view.findViewById<TextView>(R.id.tvSavings)
        val btnExport = view.findViewById<Button>(R.id.btnExportReport)

        tvHomeCost.text = "₹40/day"
        tvMarketCost.text = "₹65/day"
        tvSavings.text = "₹750/month (25 days)"

        btnExport.setOnClickListener {
            Toast.makeText(requireContext(), "Report Exported!", Toast.LENGTH_SHORT).show()
        }
    }
}