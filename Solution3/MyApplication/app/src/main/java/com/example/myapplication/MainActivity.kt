package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.Button
import androidx.recyclerview.widget.LinearLayoutManager

class MainActivity : AppCompatActivity() {
    val friends = ArrayList<Friend>()

    val adapter: FriendAdapter = FriendAdapter(friends) { friend ->
        val intent = Intent(this, EditFriendActivity::class.java)
        intent.putExtra("friend", friend)
        intent.putExtra("index", friends.indexOf(friend))
        startActivityForResult(intent, 2)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("MainActivity", "App started")
        setContentView(R.layout.view_user)
    }

    override fun onStart() {
        super.onStart()

        val recyclerView = findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.recycler_friends)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val registerButton = findViewById<Button>(R.id.button_register_new)
        registerButton.setOnClickListener {
            val intent = Intent(this, AddFriendActivity::class.java)
            startActivityForResult(intent, 1)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            val newFriend = data.getSerializableExtra("friend")
            if (newFriend is Friend) {
                friends.add(newFriend)
                adapter.notifyItemInserted(friends.size - 1)
            }
            Toast.makeText(this, "Friend added successfully", Toast.LENGTH_SHORT).show()
        } else if (requestCode == 2 && resultCode == RESULT_OK && data != null) {
            val updatedFriend = data.getSerializableExtra("friend")
            if (updatedFriend is Friend) {
                val index = data.getIntExtra("index", -1)
                if (index != -1 && index < friends.size) {
                    friends[index] = updatedFriend
                    adapter.notifyItemChanged(index)
                    Toast.makeText(this, "Friend updated successfully", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}