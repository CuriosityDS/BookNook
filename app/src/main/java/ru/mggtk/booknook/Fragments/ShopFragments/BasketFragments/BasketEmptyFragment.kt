package ru.mggtk.booknook.Fragments.ShopFragments.BasketFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.mggtk.booknook.R
import ru.mggtk.booknook.databinding.FragmentBasketBinding
import ru.mggtk.booknook.databinding.FragmentBasketEmptyBinding

class BasketEmptyFragment : Fragment() {
    private lateinit var binding: FragmentBasketEmptyBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentBasketEmptyBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = BasketEmptyFragment()
    }
}