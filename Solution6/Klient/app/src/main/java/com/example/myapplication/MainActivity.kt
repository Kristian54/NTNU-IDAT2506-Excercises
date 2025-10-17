package com.example.myapplication

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textViewReceived = findViewById< TextView>(R.id.textViewReceived)
        val textViewSent = findViewById< TextView>(R.id.textViewSent)
        val sendButton = findViewById<android.widget.Button>(R.id.buttonSend)
        val textField = findViewById<android.widget.EditText>(R.id.editMessage)
        Client(textViewReceived, textViewSent, sendButton, textField).start()
    }
}