package com.example.pashuaahar.ui.tips

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.pashuaahar.R

class TipsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tips, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnCallVet = view.findViewById<Button>(R.id.btnCallVet)

        btnCallVet.setOnClickListener {
            Toast.makeText(requireContext(),
                "Veterinarian: Dr. Sharma\nPhone: +91-9876543210",
                Toast.LENGTH_LONG).show()
        }
    }
}