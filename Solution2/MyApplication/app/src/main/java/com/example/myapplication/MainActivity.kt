package com.example.myapplication

import android.app.ComponentCaller
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("MainActivity", "onCreate called")
        super.onCreate(savedInstanceState)

        val intent = Intent("myapplication.RandomValueActivity")
        intent.putExtra("min", 0)
        intent.putExtra("max", 100)
        startActivityForResult(intent, 1)

        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()

        val multiplyButton = findViewById<TextView>(R.id.button_multiply)
        val addButton = findViewById<TextView>(R.id.button_add)

        multiplyButton.setOnClickListener { multiplyButtonClicked() }
        addButton.setOnClickListener { addButtonClicked() }
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
            val randomValue = data.getIntExtra("randomValue", -1)
            val textView = findViewById<TextView>(R.id.text_view_answer)
            textView.text = randomValue.toString()
        }
    }

    private fun multiplyButtonClicked() {
        Log.d("MainActivity", "Multiply button clicked")
    }

    private fun addButtonClicked() {
        Log.d("MainActivity", "Add button clicked")
    }
}