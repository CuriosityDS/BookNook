package ru.mggtk.booknook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import ru.mggtk.booknook.Fragments.ErrorFragments.NoInternetFragment

class ErrorActivity : AppCompatActivity() {
    companion object {
        const val ERROR_MESSAGE_KEY = "error_message"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_error)
        val errorMessage = intent.getStringExtra(ERROR_MESSAGE_KEY)
        Toast.makeText(this,"${errorMessage}",Toast.LENGTH_SHORT ).show()
        loadErrorFragment(errorMessage)
    }
    private fun loadErrorFragment(errorMessage: String?) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        when (errorMessage) {
            "No internet connection" -> {
                val noInternetFragment = NoInternetFragment()
                fragmentTransaction.replace(R.id.Frame, noInternetFragment)
            }
            // Чё нибудь еще надо добавить
            else -> {
                val otherErrorFragment = NoInternetFragment()
                fragmentTransaction.replace(R.id.Frame, otherErrorFragment)
            }
        }

        fragmentTransaction.commit()
    }
}