package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreateOptionsMenu(meny: Menu): Boolean {
        super.onCreateOptionsMenu(meny)
        meny.add("Kristian")
        meny.add("Garder")
        Log.d("Solution1" , "Menu constructed")
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.title!! == "Kristian") {
            Log.w("FirstName", "First name pressed")
        } else if (item.title!! == "Garder") {
            Log.e("LastName", "Last name pressed")
        }
        return true
    }
}