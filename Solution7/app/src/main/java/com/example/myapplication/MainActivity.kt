package com.example.myapplication

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var activity_main: ActivityMainBinding
    private lateinit var myPreferenceManager: MyPreferenceManager

    override fun onCreate(savedInstance: Bundle?) {
        super.onCreate(savedInstance)
        setContentView(R.layout.activity_main)

        activity_main = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activity_main.root)

        myPreferenceManager = MyPreferenceManager(this)
        myPreferenceManager.updateBackgroundColor()
    }

    override fun onStart() {
        super.onStart()
        myPreferenceManager.updateBackgroundColor();
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_bar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                val intent = android.content.Intent(this, SettingsActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}