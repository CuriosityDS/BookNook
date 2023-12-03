package ru.mggtk.booknook.Fragments.ShopFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.mggtk.booknook.Fragments.ShopFragments.BasketFragments.BasketEmptyFragment
import ru.mggtk.booknook.Fragments.ShopFragments.BasketFragments.BasketFullFragment
import ru.mggtk.booknook.R
import ru.mggtk.booknook.ViewModels.BasketViewModel
import ru.mggtk.booknook.databinding.FragmentBasketBinding
import ru.mggtk.booknook.databinding.FragmentBasketEmptyBinding
import ru.mggtk.booknook.databinding.FragmentMainBinding


class BasketFragment : Fragment() {

    private lateinit var basketViewModel: BasketViewModel
    private lateinit var binding: FragmentBasketBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBasketBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        basketViewModel = ViewModelProvider(requireActivity()).get(BasketViewModel::class.java)

        // Проверяем, есть ли товары в корзине
        if (basketViewModel.basketItems.value?.isNotEmpty() == true) {
            loadFullBasketFragment()
        } else {
            loadEmptyBasketFragment()
        }
    }

    private fun loadEmptyBasketFragment() {
        childFragmentManager.beginTransaction()
            .replace(R.id.MainScreenBasket, BasketEmptyFragment())
            .commit()
    }

    private fun loadFullBasketFragment() {
        childFragmentManager.beginTransaction()
            .replace(R.id.MainScreenBasket, BasketFullFragment())
            .commit()
    }
}
