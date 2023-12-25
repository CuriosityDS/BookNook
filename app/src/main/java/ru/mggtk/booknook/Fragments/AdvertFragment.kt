package ru.mggtk.booknook.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.mggtk.booknook.Adapters.ViewPagerAdapter
import ru.mggtk.booknook.R
import ru.mggtk.booknook.databinding.FragmentAdvertBinding
import ru.mggtk.booknook.databinding.FragmentHomeBinding

class AdvertFragment : Fragment() {
    private lateinit var binding: FragmentAdvertBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentAdvertBinding.inflate(inflater, container, false)
        binding.viewPagerAdvert.adapter = ViewPagerAdapter()
        binding.viewPagerAdvert.isUserInputEnabled = false
        showProgress()
        return binding.root
    }
    private fun showProgress() {
        try {
            Thread {
                for (i in 0..23) {
                    binding.viewPagerAdvert.setCurrentItem(i, true)
                    Thread.sleep(6000)
                }
            }.start()
        }catch (e: java.lang.Exception){
            showProgress()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AdvertFragment().apply {
            }
    }
}