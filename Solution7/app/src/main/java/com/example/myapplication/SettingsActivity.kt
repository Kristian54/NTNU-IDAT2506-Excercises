package com.example.myapplication

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.example.myapplication.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity(), SharedPreferences.OnSharedPreferenceChangeListener, Preference.SummaryProvider<ListPreference> {
    private lateinit var ui: ActivitySettingsBinding
    private lateinit var myPreferenceManager: MyPreferenceManager

    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        ui = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(ui.root)

        myPreferenceManager = MyPreferenceManager(this)
        myPreferenceManager.updateBackgroundColor()

        supportFragmentManager.beginTransaction().replace(R.id.settings_container, SettingsFragment()).commit()

        myPreferenceManager.registerListener(this)

        ui.buttonSave.setOnClickListener {
            finish()
        }
    }


    override fun onSharedPreferenceChanged(
        sharedPreferences: SharedPreferences?,
        key: String?
    ) {
        if (key == "background_color") {
            myPreferenceManager.updateBackgroundColor()
        }
    }

    override fun provideSummary(preference: ListPreference): CharSequence? {
        return when (preference.key) {
            "background_color" -> preference.entry
            else -> "Unknown Preference"
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        myPreferenceManager.unregisterListener(this)
    }

    class SettingsFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: android.os.Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.preference_screen, rootKey)
        }
    }
}