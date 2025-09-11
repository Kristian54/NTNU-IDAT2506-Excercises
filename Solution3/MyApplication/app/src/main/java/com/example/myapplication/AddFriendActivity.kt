package com.example.myapplication

import android.app.Activity
import android.content.Intent
import android.widget.EditText

class AddFriendActivity : Activity() {
    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()

        setContentView(R.layout.register_user)
        val nameTextBox = findViewById<EditText>(R.id.edittext_add_name)
        val birthdateTextBox = findViewById<EditText>(R.id.edittext_add_birthdate)
        val registerButton = findViewById<android.widget.Button>(R.id.button_add)

        registerButton.setOnClickListener {
            val name = nameTextBox.text.toString()
            val birthdate = birthdateTextBox.text.toString()
            addFriend(name, birthdate, intent)
            finish()
        }
    }

    fun addFriend(name: String, birthdate: String, intent: android.content.Intent) {
        val resultIntent = Intent()
        resultIntent.putExtra("friend", Friend(name, birthdate))
        setResult(RESULT_OK, resultIntent)
    }
}