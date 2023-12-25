package ru.mggtk.booknook.Fragments.ShopFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.mggtk.booknook.Adapters.ViewPagerAdapter
import ru.mggtk.booknook.Fragments.AdvertFragment
import ru.mggtk.booknook.Fragments.ShopFragments.Products.ProductsHomeFragment
import ru.mggtk.booknook.R
import ru.mggtk.booknook.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        replaceFragment(ProductsHomeFragment(),R.id.productHome)
        replaceFragment(AdvertFragment(),R.id.advertHolder)
        return binding.root
    }

    private fun replaceFragment(fragment: Fragment, placeFragment:Int) {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(placeFragment, fragment)
        transaction.commit()
    }

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}