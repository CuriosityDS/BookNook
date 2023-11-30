package ru.mggtk.booknook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ErrorActivity : AppCompatActivity() {
    companion object {
        const val ERROR_MESSAGE_KEY = "error_message"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_error)
    }
}