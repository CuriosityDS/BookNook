package ru.mggtk.booknook.Fragments.ErrorFragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.mggtk.booknook.MainActivity
import ru.mggtk.booknook.SplashScreen.SplashScreenActivity
import ru.mggtk.booknook.databinding.FragmentNoInternetBinding

class NoInternetFragment : Fragment() {
    private lateinit var binding: FragmentNoInternetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentNoInternetBinding.inflate(inflater,container, false)
        binding.updateButton.setOnClickListener {
            update()
        }
        return binding.root
    }

    companion object {
    }

    fun update() {
        // Создаем Intent для запуска SplashScreenActivity
        val intent = Intent(activity, SplashScreenActivity::class.java)
        // Запускаем активити
        startActivity(intent)
        // Опционально: закрываем текущую активити (фрагмент)
        activity?.finish()
    }

}