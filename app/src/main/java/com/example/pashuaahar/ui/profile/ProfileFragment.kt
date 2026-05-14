package com.example.pashuaahar.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.pashuaahar.R

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val spinnerBreed = view.findViewById<Spinner>(R.id.spinnerBreed)
        val etAge = view.findViewById<EditText>(R.id.etAge)
        val etWeight = view.findViewById<EditText>(R.id.etWeight)
        val etMilkYield = view.findViewById<EditText>(R.id.etMilkYield)
        val btnCalculate = view.findViewById<Button>(R.id.btnCalculateRecipe)

        val breeds = arrayOf("Jersey", "HF", "Desi", "Sahiwal")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, breeds)
        spinnerBreed.adapter = adapter

        btnCalculate.setOnClickListener {
            val breed = spinnerBreed.selectedItem.toString()
            val age = etAge.text.toString()
            val weight = etWeight.text.toString()
            val milkYield = etMilkYield.text.toString()

            if (age.isNotEmpty() && weight.isNotEmpty() && milkYield.isNotEmpty()) {
                Toast.makeText(requireContext(),
                    "Profile: $breed, Age: $age, Weight: $weight kg, Milk: $milkYield L/day",
                    Toast.LENGTH_SHORT).show()

                findNavController().navigate(R.id.recipeFragment)
            } else {
                Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
}