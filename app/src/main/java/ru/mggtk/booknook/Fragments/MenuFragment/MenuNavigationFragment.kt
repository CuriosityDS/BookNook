package ru.mggtk.booknook.Fragments.MenuFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import ru.mggtk.booknook.Fragments.ShopFragments.BasketFragment
import ru.mggtk.booknook.Fragments.ShopFragments.HomeFragment
import ru.mggtk.booknook.Fragments.ShopFragments.ProfileFragment
import ru.mggtk.booknook.Fragments.ShopFragments.SearchFragment
import ru.mggtk.booknook.R
import ru.mggtk.booknook.ViewModels.BasketViewModel
import ru.mggtk.booknook.databinding.FragmentMainBinding
import ru.mggtk.booknook.databinding.FragmentMenuNavigationBinding

class MenuNavigationFragment : Fragment() {
    private lateinit var binding: FragmentMenuNavigationBinding
    private lateinit var basketViewModel: BasketViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMenuNavigationBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home -> {
                    replaceFragment(HomeFragment())
                    true
                }

                R.id.search -> {
                    replaceFragment(SearchFragment())
                    true
                }

                R.id.profile -> {
                    replaceFragment(ProfileFragment())
                    true
                }

                R.id.basket -> {
                    replaceFragment(BasketFragment())
                    true
                }

                else -> false
            }
        }
        basketViewModel = ViewModelProvider(requireActivity()).get(BasketViewModel::class.java)

        // Наблюдайте за изменениями в корзине
        basketViewModel.basketItems.observe(viewLifecycleOwner, { basketItems ->
            updateBasketIcon(basketItems.isNotEmpty())
        })
        return view
    }

    private fun updateBasketIcon(hasItems: Boolean) {
        val menu = binding.bottomNavigationView.menu
        val basketMenuItem = menu.findItem(R.id.basket)

        // Установите разные иконки в зависимости от наличия товаров в корзине
        basketMenuItem.setIcon(if (hasItems) R.drawable.ic_basket_filled else R.drawable.basketicon)
    }

    companion object {
        @JvmStatic
        fun newInstance() = MenuNavigationFragment()
    }
    private fun replaceFragment(fragment: Fragment) {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.MainView, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}