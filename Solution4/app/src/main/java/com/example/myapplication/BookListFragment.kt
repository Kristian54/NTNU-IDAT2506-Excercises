package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.ListView
import androidx.fragment.app.Fragment

class BookListFragment : Fragment(R.layout.book_list_fragment) {

    val books = ArrayList<Book>()
    var selectedBookPosition = 0;

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBooks();

        val listView = view.findViewById< ListView>(R.id.listview_books)
        listView.adapter = BookAdapter(requireContext(), books)

        listView.onItemClickListener = android.widget.AdapterView.OnItemClickListener { parent, view, position, id ->
            val book = books[position]
            val fragment = parentFragmentManager.findFragmentById(R.id.books) as BookFragment?
            selectedBookPosition = position
            fragment?.setBook(book)
        }
    }

    fun selectNextBook() {
        if (selectedBookPosition < books.size - 1) {
            selectedBookPosition++
        } else {
            selectedBookPosition = 0
        }
        val book = books[selectedBookPosition]
        val fragment = parentFragmentManager.findFragmentById(R.id.books) as BookFragment?
        fragment?.setBook(book)
    }

    fun selectPreviousBook() {
        if (selectedBookPosition > 0) {
            selectedBookPosition--
        } else {
            selectedBookPosition = books.size - 1
        }
        val book = books[selectedBookPosition]
        val fragment = parentFragmentManager.findFragmentById(R.id.books) as BookFragment?
        fragment?.setBook(book)
    }

    fun initBooks() {
        val book1 = Book("Harry Potter and the Philosopher's Stone", "A book about a young wizard named Harry Potter who discovers his magical heritage on his eleventh birthday.", "harrypotter")
        val book2 = Book("The Hobbit", "A fantasy novel by J.R.R. Tolkien that follows the quest of home-loving Bilbo Baggins to win a share of the treasure guarded by the dragon, Smaug.", "hobbit")
        val book3 = Book("1984", "A dystopian social science fiction novel and cautionary tale, written by the English writer George Orwell.", "georgeorwell")
        val book4 = Book("To Kill a Mockingbird", "A novel by Harper Lee published in 1960. It was immediately successful, winning the Pulitzer Prize, and has become a classic of modern American literature.", "mockingbird")
        val book5 = Book("Pride and Prejudice", "A romantic novel of manners written by Jane Austen in 1813.", "prideandprejudice")
        val book6 = Book("The Great Gatsby", "A 1925 novel by American writer F. Scott Fitzgerald. Set in the Jazz Age on Long Island, the novel depicts narrator Nick Carraway's interactions with mysterious millionaire Jay Gatsby and Gatsby's obsession to reunite with his former lover, Daisy Buchanan.", "gatsby")
        books.addAll(listOf(book1, book2, book3, book4, book5, book6))
    }
}