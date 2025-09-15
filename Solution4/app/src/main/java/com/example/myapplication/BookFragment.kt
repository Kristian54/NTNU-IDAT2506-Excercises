package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment

class BookFragment : Fragment(R.layout.book_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    public fun setBook(book: Book) {
        val titleView = view?.findViewById<TextView>(R.id.book_title)
        val descriptionView = view?.findViewById<TextView>(R.id.book_description)
        val imageView = view?.findViewById<ImageView>(R.id.book_cover)
        val imageResId = resources.getIdentifier(book.imageName, "drawable", requireContext().packageName)
        titleView?.text = book.title
        descriptionView?.text = book.description
        imageView?.setImageResource(imageResId)
    }

}