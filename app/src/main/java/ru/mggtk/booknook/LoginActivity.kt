package ru.mggtk.booknook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import ru.mggtk.booknook.SplashScreen.SplashScreenWelcomeActivity
import ru.mggtk.booknook.check.InternetCheckUtil

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val loginButton:Button = findViewById(R.id.button)
        val email:EditText = findViewById(R.id.editTextText)
        val password:EditText = findViewById(R.id.editTextTextPassword)
        loginButton.setOnClickListener(){
            if (InternetCheckUtil.isInternetAvailable(this)) {
                if (email.text.toString() == "1" && password.text.toString() == "1" ) {
                    val intent = Intent(this, SplashScreenWelcomeActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                else {
                    email.hint = getString(R.string.errorauthentication)
                    email.setText("")
                    password.setText("")
                }
            } else {
                openErrorActivity("No internet connection")
            }
        }
    }
    private fun openErrorActivity(errorMessage: String) {
        val intent = Intent(this, ErrorActivity::class.java)
        intent.putExtra(ErrorActivity.ERROR_MESSAGE_KEY, errorMessage)
        startActivity(intent)
        finish()
    }
}