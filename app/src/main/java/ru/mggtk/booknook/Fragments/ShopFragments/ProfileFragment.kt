package ru.mggtk.booknook.Fragments.ShopFragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.app.ActivityCompat.invalidateOptionsMenu
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.mggtk.booknook.MainActivity
import ru.mggtk.booknook.R
import ru.mggtk.booknook.check.LanguageManager
import ru.mggtk.booknook.databinding.FragmentBasketBinding
import ru.mggtk.booknook.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var languageManager: LanguageManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)

        languageManager = LanguageManager(requireContext())

        val btnChangeLanguage: Button = binding.btnChangeLanguage
        btnChangeLanguage.setOnClickListener {
            showLanguageSelectionDialog()
        }
        return binding.root
    }
    private fun showLanguageSelectionDialog() {
        val languages = arrayOf("en", "ru")
        val languageNames = arrayOf("English", "Русский")

        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle(R.string.language_selection)
            .setItems(languageNames) { _, which ->
                val selectedLanguage = languages[which]
                languageManager.changeAppLanguage(selectedLanguage)
                binding.textView8.text = getString(R.string.welcometext)
                binding.textView9.text = getString(R.string.settings)
                binding.btnChangeLanguage.text = getString(R.string.language_selection)
            }

        builder.create().show()
    }

    companion object {
        @JvmStatic
        fun newInstance() = ProfileFragment()
    }
}