package ru.mggtk.booknook.Fragments.ShopFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.mggtk.booknook.Fragments.ShopFragments.BasketFragments.BasketEmptyFragment
import ru.mggtk.booknook.R
import ru.mggtk.booknook.databinding.FragmentBasketBinding
import ru.mggtk.booknook.databinding.FragmentBasketEmptyBinding
import ru.mggtk.booknook.databinding.FragmentMainBinding


class BasketFragment : Fragment() {
    private lateinit var binding: FragmentBasketBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentBasketBinding.inflate(inflater, container, false)
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.MainScreenBasket, BasketEmptyFragment())
        transaction.commit()
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = BasketFragment()
    }
}