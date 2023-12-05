package ru.mggtk.booknook.SplashScreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.graphics.drawable.Animatable
import android.graphics.drawable.AnimationDrawable
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import ru.mggtk.booknook.ErrorActivity
import ru.mggtk.booknook.InletActivity
import ru.mggtk.booknook.R
import ru.mggtk.booknook.check.InternetCheckUtil
import ru.mggtk.booknook.check.LanguageManager

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val languageManager = LanguageManager(this)
        val savedLanguage = languageManager.getAppLanguage()
        // Установите язык приложения
        languageManager.changeAppLanguage(savedLanguage)
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

    companion object{
        private val SPLASH_DISPLAY_LENGTH = 2500L
    }
}
