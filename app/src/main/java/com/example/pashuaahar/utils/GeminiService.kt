package com.example.pashuaahar.utils

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class GeminiService {

    private val apiKey = "AlzaSyBefi0BZTuK0LYZNmfJUmQY8fhqxYS3jKU"
    private val apiUrl = "https://generativelanguage.googleapis.com/v1beta/models/gemini-pro:generateContent"

    suspend fun generateRecipe(
        breed: String,
        age: String,
        weight: String,
        milkYield: String
    ): String = withContext(Dispatchers.IO) {
        try {
            val prompt = """
                Create a cattle feed recipe for:
                Breed: $breed
                Age: $age years
                Weight: $weight kg
                Milk Yield: $milkYield liters/day
                
                Provide feed mix recommendation with ingredients and cost.
            """.trimIndent()

            val requestBody = """
                {
                    "contents": [
                        {
                            "parts": [
                                {
                                    "text": "$prompt"
                                }
                            ]
                        }
                    ]
                }
            """.trimIndent()

            val url = URL("$apiUrl?key=$apiKey")
            val connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "POST"
            connection.setRequestProperty("Content-Type", "application/json")
            connection.doOutput = true

            connection.outputStream.bufferedWriter().use {
                it.write(requestBody)
            }

            val responseCode = connection.responseCode
            if (responseCode == HttpURLConnection.HTTP_OK) {
                val response = BufferedReader(InputStreamReader(connection.inputStream))
                    .use { it.readText() }

                parseGeminiResponse(response)
            } else {
                "Error: Unable to generate recipe."
            }

        } catch (e: Exception) {
            Log.e("GeminiService", "Error: ${e.message}")
            "Error: ${e.message}"
        }
    }

    private fun parseGeminiResponse(response: String): String {
        return try {
            val jsonObject = JSONObject(response)
            val candidates = jsonObject.getJSONArray("candidates")
            if (candidates.length() > 0) {
                val content = candidates.getJSONObject(0).getJSONObject("content")
                val parts = content.getJSONArray("parts")
                if (parts.length() > 0) {
                    parts.getJSONObject(0).getString("text")
                } else {
                    "No recipe generated"
                }
            } else {
                "No response from API"
            }
        } catch (e: Exception) {
            "Error: ${e.message}"
        }
    }
}