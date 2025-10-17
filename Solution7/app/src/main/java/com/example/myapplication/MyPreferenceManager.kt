package com.example.myapplication

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager

class MyPreferenceManager(private val activity: AppCompatActivity) {
    private val resources = activity.resources
    private val preferences = PreferenceManager.getDefaultSharedPreferences(activity)
    private val editor: SharedPreferences.Editor = preferences.edit()

    fun updateBackgroundColor() {
        val backgroundColorValues = resources.getStringArray(R.array.background_values)
        val value = preferences.getString("background_color", backgroundColorValues[0])
        when (value) {
            backgroundColorValues[0] -> activity.window.decorView.setBackgroundColor(
                resources.getColor(R.color.white, activity.theme)
            )
            backgroundColorValues[1] -> activity.window.decorView.setBackgroundColor(
                resources.getColor(R.color.red, activity.theme)
            )
            backgroundColorValues[2] -> activity.window.decorView.setBackgroundColor(
                resources.getColor(R.color.green, activity.theme)
            )
            backgroundColorValues[3] -> activity.window.decorView.setBackgroundColor(
                resources.getColor(R.color.blue, activity.theme)
            )
        }
    }

    fun registerListener(listener: SharedPreferences.OnSharedPreferenceChangeListener) {
        preferences.registerOnSharedPreferenceChangeListener(listener)
    }

    fun unregisterListener(listener: SharedPreferences.OnSharedPreferenceChangeListener) {
        preferences.unregisterOnSharedPreferenceChangeListener(listener)
    }
}