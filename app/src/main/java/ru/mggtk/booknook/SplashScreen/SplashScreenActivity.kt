package ru.mggtk.booknook.SplashScreen

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Handler
import android.os.Looper
import ru.mggtk.booknook.ErrorActivity
import ru.mggtk.booknook.InletActivity
import ru.mggtk.booknook.MainActivity
import ru.mggtk.booknook.R
import ru.mggtk.booknook.check.InternetCheckUtil

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (InternetCheckUtil.isInternetAvailable(this)) {
            setContentView(R.layout.activity_splash_screen)
            scheduleSplashScreen()
        } else {
            setContentView(R.layout.activity_splash_screen)
            openErrorActivity("No internet connection")
        }
    }


    private fun openErrorActivity(errorMessage: String) {
        val splashScreenDuration: Long = 4000
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this@SplashScreenActivity, ErrorActivity::class.java)
            intent.putExtra(ErrorActivity.ERROR_MESSAGE_KEY, errorMessage)
            startActivity(intent)
            finish()
        }, splashScreenDuration)
    }

    private fun scheduleSplashScreen() {
        val splashScreenDuration: Long = 1000
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this@SplashScreenActivity, InletActivity::class.java)
            startActivity(intent)
            finish()
        }, splashScreenDuration)
    }
}
