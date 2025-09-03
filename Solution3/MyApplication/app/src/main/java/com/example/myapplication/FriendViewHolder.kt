package com.example.myapplication

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FriendViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val name: TextView = itemView.findViewById(R.id.text_name)
    val birthdate: TextView = itemView.findViewById(R.id.text_birthdate)
    val editButton: Button = itemView.findViewById(R.id.button_edit)
}