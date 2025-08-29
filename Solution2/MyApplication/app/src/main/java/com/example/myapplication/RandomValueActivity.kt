package com.example.myapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RandomValueActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("MainActivity", "onCreate called")
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        val min = intent.getIntExtra("min", 0)
        val max = intent.getIntExtra("max", 100)
        val randomOne = generateRandomValue(min, max)
        val randomTwo = generateRandomValue(min, max)

        //showRandomValueToast(random)
        val resultIntent = Intent()
        resultIntent.putExtra("randomValueOne", randomOne)
        resultIntent.putExtra("randomValueTwo", randomTwo)
        setResult(RESULT_OK, resultIntent)
        finish()
    }

    fun showRandomValueToast(value: Int) {
        Toast.makeText(this, "Random Value: $value", Toast.LENGTH_SHORT).show()
    }

    fun generateRandomValue(min: Int, max: Int): Int {
        return (min..max).random()
    }
}