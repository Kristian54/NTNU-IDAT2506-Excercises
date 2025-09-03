package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class FriendAdapter (
    private val friends: List<Friend>,
    private val onEditClick: (Friend) -> Unit
) : RecyclerView.Adapter<FriendViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FriendViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_friend, parent, false)
        return FriendViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: FriendViewHolder,
        position: Int
    ) {
        val friend = friends[position]
        holder.name.text = friend.name
        holder.birthdate.text = friend.birthdate
        holder.editButton.setOnClickListener { onEditClick(friend) }
    }

    override fun getItemCount(): Int {
        return friends.size
    }

}