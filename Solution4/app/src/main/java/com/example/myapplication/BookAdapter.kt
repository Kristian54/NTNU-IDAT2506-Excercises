package com.example.myapplication

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class BookAdapter(context: Context, books: List<Book>) : ArrayAdapter<Book>(context, android.R.layout.simple_list_item_1, books) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: View.inflate(context, android.R.layout.simple_list_item_1, null)
        val book = getItem(position)
        (view as TextView).text = book?.title ?: ""
        return view
    }
}