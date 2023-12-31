package ru.mggtk.booknook.Fragments.ShopFragments

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import kotlinx.coroutines.Dispatchers.Main
import ru.mggtk.booknook.Fragments.AdvertFragment
import ru.mggtk.booknook.Fragments.MenuFragment.MenuNavigationFragment
import ru.mggtk.booknook.R
import ru.mggtk.booknook.SplashScreen.SplashScreenWelcomeActivity
import ru.mggtk.booknook.check.LanguageManager
import ru.mggtk.booknook.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var languageManager: LanguageManager
    private var isDarkTheme: Boolean
        get() = requireActivity().getPreferences(Context.MODE_PRIVATE).getBoolean("isDarkTheme", false)
        set(value) {
            requireActivity().getPreferences(Context.MODE_PRIVATE).edit().putBoolean("isDarkTheme", value).apply()
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isDarkTheme = isDarkTheme
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        replaceFragment(AdvertFragment(),R.id.advertHolderProfile)
        binding = FragmentProfileBinding.inflate(inflater, container, false)

        languageManager = LanguageManager(requireContext())

        val btnChangeLanguage: Button = binding.btnChangeLanguage
        btnChangeLanguage.setOnClickListener {
            showLanguageSelectionDialog()
        }
        binding.btnAbout.setOnClickListener {
            showAboutDialog(requireContext())
        }

        return binding.root
    }
    private fun showAboutDialog(context: Context) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("${getString(R.string.about)}")
        val message = getString(R.string.abouttext)
        builder.setMessage(message)

        builder.setPositiveButton("OK") { dialog, which ->
            dialog.dismiss()
        }

        val dialog = builder.create()
        dialog.show()
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
                binding.btnAbout.text = getString(R.string.about)
                val intent = Intent (getActivity(), SplashScreenWelcomeActivity::class.java)
                getActivity()?.startActivity(intent)
            }

        builder.create().show()
    }
    private fun replaceFragment(fragment: Fragment, placeFragment:Int) {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(placeFragment, fragment)
        transaction.commit()
    }


    companion object {
        @JvmStatic
        fun newInstance() = ProfileFragment()
    }
}