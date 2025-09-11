package com.example.myapplication

import android.app.Activity
import android.content.Intent
import android.widget.EditText

class EditFriendActivity : Activity() {
    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()

        setContentView(R.layout.edit_user)
        val nameTextBox = findViewById<EditText>(R.id.edittext_edit_name)
        val birthdateTextBox = findViewById<EditText>(R.id.edittext_edit_birthdate)
        val saveButton = findViewById<android.widget.Button>(R.id.button_save)

        val name = nameTextBox.text.toString()
        val birthdate = birthdateTextBox.text.toString()

        val friend = intent.getSerializableExtra("friend")
        if (friend is Friend) {
            nameTextBox.setText(friend.name)
            birthdateTextBox.setText(friend.birthdate)
        }

        saveButton.setOnClickListener {
            if (friend is Friend) {
                friend.name = nameTextBox.text.toString()
                friend.birthdate = birthdateTextBox.text.toString()
            }

            val resultIntent = Intent()
            resultIntent.putExtra("friend", friend)
            resultIntent.putExtra("index", intent.getIntExtra("index", -1))
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }

    fun addFriend(name: String, birthdate: String, intent: android.content.Intent) {
        val resultIntent = Intent()
        resultIntent.putExtra("friend", Friend(name, birthdate))
        setResult(RESULT_OK, resultIntent)
    }
}