package com.example.myapplication

import android.app.ComponentCaller
import android.content.Intent
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ntnu.leksjon_05.http.HTTP
import ntnu.leksjon_05.http.HttpWrapper

const val URL = "https://bigdata.idi.ntnu.no/mobil/tallspill.jsp"

class MainActivity : AppCompatActivity() {
    private val http: HttpWrapper = HttpWrapper(URL)


    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        setContentView(R.layout.activity_main)

        var sendInfoButton: Button = findViewById(R.id.sendInfo)
        var makeGuessButton: Button = findViewById(R.id.makeGuess)

        var nameField: EditText = findViewById(R.id.name)
        var cardNumberField: EditText = findViewById(R.id.cardNumber)
        var guessField: EditText = findViewById(R.id.guess)

        sendInfoButton.setOnClickListener {
            Log.d("MainActivity", "Send Info button clicked")
            val params = HashMap<String, String>()
            val username: String = nameField.text.toString()
            val cardNumber: String = cardNumberField.text.toString()
            params["navn"] = username
            params["kortnummer"] = cardNumber
            sendGet(params)
        }

        makeGuessButton.setOnClickListener {
            Log.d("MainActivity", "Make Guess button clicked")
            val params = HashMap<String, String>()
            val guess: String = guessField.text.toString()
            params["tall"] = guess
            sendGet(params)
        }
    }

    private fun sendGet(params: HashMap<String, String>) {
        lifecycleScope.launch(Dispatchers.IO) {
            Log.d("MainActivity", "Sending GET with params: $params")
            val response = http.get(params)
            withContext(Dispatchers.Main) {
                Log.d("MainActivity", "Response received: $response")
                updateResponseLabel(response)
            }
        }
    }

    private fun updateResponseLabel(response: String) {
        val responseLabel: TextView = findViewById(R.id.responseText)
        responseLabel.text = response
        Log.d("MainActivity", "Response updated: $response")
    }
}