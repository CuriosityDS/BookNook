package ru.mggtk.booknook.Fragments.ShopFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import ru.mggtk.booknook.Adapters.BooksAdapter
import ru.mggtk.booknook.Fragments.ShopFragments.Products.ProductsHomeFragment
import ru.mggtk.booknook.R
import ru.mggtk.booknook.ViewModels.BasketViewModel
import ru.mggtk.booknook.databinding.FragmentHomeBinding
import ru.mggtk.booknook.databinding.FragmentProductsHomeBinding
import ru.mggtk.booknook.databinding.FragmentSearchBinding
import ru.mggtk.booknook.dataclass.Book


class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    companion object {
        @JvmStatic
        fun newInstance() = SearchFragment()
    }

}