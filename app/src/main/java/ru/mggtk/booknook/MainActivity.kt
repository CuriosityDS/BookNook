package ru.mggtk.booknook

import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.mggtk.booknook.Fragments.MainFragment.MainFragment
import ru.mggtk.booknook.check.InternetCheckUtil
import ru.mggtk.booknook.check.NetworkChangeReceiver

class MainActivity : AppCompatActivity() {
    private val networkChangeReceiver = NetworkChangeReceiver(object :
        NetworkChangeReceiver.OnNetworkChangedListener {
        override fun onNetworkAvailable() {
            // Обработка доступности интернета
        }

        override fun onNetworkUnavailable() {
            openErrorActivity("No internet connection")
        }
    })
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (InternetCheckUtil.isInternetAvailable(this)) {
            // Если есть интернет
            supportFragmentManager.beginTransaction()
                .replace(R.id.placeHolder, MainFragment.newInstance())
                .commit()
        } else {
            // Если нет интернета
            openErrorActivity("No internet connection")
        }
        setContentView(R.layout.activity_main)
        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(networkChangeReceiver, filter)
    }

    private fun openErrorActivity(errorMessage: String) {
        val intent = Intent(this, ErrorActivity::class.java)
        intent.putExtra(ErrorActivity.ERROR_MESSAGE_KEY, errorMessage)
        startActivity(intent)
        finish()
    }

}