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
import ru.mggtk.booknook.MainActivity
import ru.mggtk.booknook.R

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (isInternetAvailable()) {
            setContentView(R.layout.activity_splash_screen)
            scheduleSplashScreen()
        } else {
            setContentView(R.layout.activity_splash_screen)
            openErrorActivity("No internet connection")
        }
    }

    private fun isInternetAvailable(): Boolean {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities = connectivityManager.activeNetwork ?: return false
            val activeNetwork =
                connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false

            return when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        } else {
            val networkInfo = connectivityManager.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnected
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
        val splashScreenDuration: Long = 4000
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this@SplashScreenActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, splashScreenDuration)
    }
}
