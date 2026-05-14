package com.example.pashuaahar.ui.recipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.pashuaahar.R

class RecipeFragment : Fragment() {

    private var generatedRecipe = "Enter cow details in Profile tab to generate recipe using AI."

    fun setRecipe(recipe: String) {
        this.generatedRecipe = recipe
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recipe, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvRecipeTitle = view.findViewById<TextView>(R.id.tvRecipeTitle)
        val tvIngredient1 = view.findViewById<TextView>(R.id.tvIngredient1)
        val tvIngredient2 = view.findViewById<TextView>(R.id.tvIngredient2)
        val tvCostInfo = view.findViewById<TextView>(R.id.tvCostInfo)
        val btnSaveRecipe = view.findViewById<Button>(R.id.btnSaveRecipe)

        tvRecipeTitle.text = "AI Generated Recipe"
        tvIngredient1.text = generatedRecipe
        tvIngredient2.text = ""
        tvCostInfo.text = "Powered by Google Gemini AI"

        btnSaveRecipe.setOnClickListener {
            Toast.makeText(requireContext(), "Recipe Saved!", Toast.LENGTH_SHORT).show()
        }
    }
}