package ru.mggtk.booknook.SplashScreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import ru.mggtk.booknook.InletActivity
import ru.mggtk.booknook.MainActivity
import ru.mggtk.booknook.R

class SplashScreenWelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen_welcome)
        scheduleSplashScreen()
    }
    private fun scheduleSplashScreen() {
        val splashScreenDuration: Long = 3000
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this@SplashScreenWelcomeActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, splashScreenDuration)
    }
}