package ru.mggtk.booknook.check

import android.content.Context
import java.util.Locale

class LanguageManager(private val context: Context) {

    private val preferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
    private val LANGUAGE_KEY = "language"

    fun changeAppLanguage(languageCode: String) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)

        val resources = context.resources
        val configuration = resources.configuration
        configuration.setLocale(locale)

        val displayMetrics = resources.displayMetrics
        resources.updateConfiguration(configuration, displayMetrics)

        // Сохраните выбранный язык в SharedPreferences
        preferences.edit().putString(LANGUAGE_KEY, languageCode).apply()
    }

    fun getAppLanguage(): String {
        // Если язык не сохранен, по умолчанию возвращаем "en"
        return preferences.getString(LANGUAGE_KEY, "en") ?: "en"
    }

    companion object {
        fun getSavedLanguage(context: Context): String {
            val preferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
            return preferences.getString("language", "en") ?: "en"
        }
    }
}

