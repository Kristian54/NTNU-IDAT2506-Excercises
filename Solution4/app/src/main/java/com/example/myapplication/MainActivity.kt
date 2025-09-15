package com.example.myapplication

import android.content.res.Configuration
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.book_list, BookListFragment())
                .replace(R.id.books, BookFragment())
                .commit()
        }
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)
        val bookListFragment = supportFragmentManager.findFragmentById(R.id.book_list) as BookListFragment
        if (item.title?.equals("Next") == true) {
            bookListFragment.selectNextBook()
        } else if (item.title?.equals("Previous") == true) {
            bookListFragment.selectPreviousBook()
        }
        Log.d("MainActivity", "Menu item pressed ${item.title}")
        return true
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        val layout = findViewById< LinearLayout>(R.id.linear_layout)
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            layout.orientation = LinearLayout.HORIZONTAL
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            layout.orientation = LinearLayout.VERTICAL
        }
    }
}