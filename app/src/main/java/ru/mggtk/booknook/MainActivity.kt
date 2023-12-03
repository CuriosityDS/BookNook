package ru.mggtk.booknook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.mggtk.booknook.Fragments.MainFragment.MainFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .replace(R.id.placeHolder, MainFragment.newInstance())
            .commit()
    }
}