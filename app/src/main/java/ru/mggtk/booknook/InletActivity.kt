package ru.mggtk.booknook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import ru.mggtk.booknook.check.InternetCheckUtil

class InletActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inlet)
    }

    fun login(view: View) {
        if (InternetCheckUtil.isInternetAvailable(this)) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        } else {
            openErrorActivity("No internet connection")
        }
    }

    private fun openErrorActivity(errorMessage: String) {
        val intent = Intent(this, ErrorActivity::class.java)
        intent.putExtra(ErrorActivity.ERROR_MESSAGE_KEY, errorMessage)
        startActivity(intent)
        finish()
    }
}