package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.Button
import androidx.recyclerview.widget.LinearLayoutManager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("MainActivity", "App started")
        setContentView(R.layout.view_user)
    }

    override fun onStart() {
        super.onStart()

        val friends = listOf(
            Friend("Kristian", "22.10.2002"),
            Friend("Friend", "23.12.2003"),
            Friend("Friend", "23.12.2003"),
            Friend("Friend", "23.12.2003"),
            Friend("Friend", "23.12.2003"),
            Friend("Friend", "23.12.2003"),
            Friend("Friend", "23.12.2003"),
            Friend("Friend", "23.12.2003"),
            Friend("Friend", "23.12.2003"),
            Friend("Friend", "23.12.2003"),
            Friend("Friend", "23.12.2003")
        )

        val adapter = FriendAdapter(friends) { friend ->
            Log.d("MainActivity", "Edit clicked for ${friend.name}" )
        }

        val recyclerView = findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.recycler_friends)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val registerButton = findViewById<Button>(R.id.button_register_new)
        registerButton.setOnClickListener {
            Log.d("MainActivity", "Register button clicked")
        }
    }
}