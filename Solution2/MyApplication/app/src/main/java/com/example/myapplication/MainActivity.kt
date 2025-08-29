package com.example.myapplication

import android.app.ComponentCaller
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.os.Handler

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("MainActivity", "onCreate called")
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        initiateRandomValues()
    }

    override fun onStart() {
        super.onStart()

        val multiplyButton = findViewById<TextView>(R.id.button_multiply)
        val addButton = findViewById<TextView>(R.id.button_add)

        multiplyButton.setOnClickListener { multiplyButtonClicked() }
        addButton.setOnClickListener { addButtonClicked() }
    }

    fun initiateRandomValues() {
        val intent = Intent("myapplication.RandomValueActivity")
        intent.putExtra("min", 0)
        var upperLimit: Int = 1
        try {
            upperLimit = findViewById<TextView>(R.id.edit_text_upper_limit).text.toString().toInt()
        } catch (e: NumberFormatException) {
            Toast.makeText(this, "Please input a number", Toast.LENGTH_SHORT).show()
        }
        intent.putExtra("max", upperLimit)
        startActivityForResult(intent, 1)
    }

    public override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?,
        caller: ComponentCaller
    ) {
        super.onActivityResult(requestCode, resultCode, data, caller)
        Log.d("MainActivity", "onActivityResult called")
        if (resultCode == RESULT_OK && data != null) {
            val randomValue = data.getIntExtra("randomValueOne", -1)
            val randomValueTwo = data.getIntExtra("randomValueTwo", -1)
            if (randomValue != -1 && randomValueTwo != -1) {
                val textViewOne = findViewById<TextView>(R.id.text_view_number_one)
                val textViewTwo = findViewById<TextView>(R.id.text_view_number_two)
                textViewOne.text = randomValue.toString()
                textViewTwo.text = randomValueTwo.toString()
            }
        }
    }

    private fun multiplyButtonClicked() {
        val numbers = fetchNumbers()
        if (numbers != null) {
            if (numbers.size == 2) {
                val result = numbers[0] * numbers[1]
                val userAnswer = findViewById<TextView>(R.id.edit_text_answer).text.toString()
                var userAnswerInt: Int = -1
                try {
                    userAnswerInt = userAnswer.toInt()
                } catch (e: NumberFormatException) {
                    Toast.makeText(this, "Please input a number", Toast.LENGTH_SHORT).show()
                }

                if (userAnswerInt == result) {
                    Toast.makeText(this, R.string.riktig, Toast.LENGTH_SHORT).show()
                    Handler().postDelayed({
                        initiateRandomValues()
                        Toast.makeText(this, "New numbers generated", Toast.LENGTH_SHORT).show()
                    }, 2000)
                } else {
                    Toast.makeText(this, getString(R.string.feil, result), Toast.LENGTH_SHORT).show()
                }

            }
        }
    }

    private fun addButtonClicked() {
        val numbers = fetchNumbers()
        if (numbers != null) {
            if (numbers.size == 2) {
                val result = numbers[0] + numbers[1]
                val userAnswer = findViewById<TextView>(R.id.edit_text_answer).text.toString()
                var userAnswerInt: Int = -1
                try {
                    userAnswerInt = userAnswer.toInt()
                } catch (e: NumberFormatException) {
                    Toast.makeText(this, "Please input a number", Toast.LENGTH_SHORT).show()
                }

                if (userAnswerInt == result) {
                    Toast.makeText(this, R.string.riktig, Toast.LENGTH_SHORT).show()
                    Handler().postDelayed({
                        initiateRandomValues()
                        Toast.makeText(this, "New numbers generated", Toast.LENGTH_SHORT).show()
                    }, 2000)
                } else {
                    Toast.makeText(this, getString(R.string.feil, result), Toast.LENGTH_SHORT).show()
                }

            }
        }
    }

    private fun fetchNumbers(): List<Int>? {
        val str1: String = findViewById<TextView>(R.id.text_view_number_one).text.toString()
        val str2: String = findViewById<TextView>(R.id.text_view_number_two).text.toString()

        val num1: Int
        val num2: Int

        return try {
            num1 = str1.toInt()
            num2 = str2.toInt()
            listOf(num1, num2)
        } catch (e: NumberFormatException) {
            Log.e("MainActivity", "Invalid number format", e)
            null
        }
    }
}