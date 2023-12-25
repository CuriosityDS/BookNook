package ru.mggtk.booknook.Fragments.ShopFragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.mggtk.booknook.Adapters.BooksAdapter
import ru.mggtk.booknook.Fragments.ShopFragments.Products.HorizontalSpaceItemDecoration
import ru.mggtk.booknook.Fragments.ShopFragments.Products.ProductsHomeFragment
import ru.mggtk.booknook.R
import ru.mggtk.booknook.ViewModels.BasketViewModel
import ru.mggtk.booknook.databinding.FragmentHomeBinding
import ru.mggtk.booknook.databinding.FragmentProductsHomeBinding
import ru.mggtk.booknook.databinding.FragmentSearchBinding
import ru.mggtk.booknook.dataclass.Book


class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private lateinit var booksAdapter: BooksAdapter
    private lateinit var allBooks: List<Book>
    private lateinit var basketViewModel: BasketViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        allBooks = loadAllBooks()
        basketViewModel = ViewModelProvider(requireActivity()).get(BasketViewModel::class.java)

        binding.editTextSearch.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                    binding.scrollView.post {
                        binding.scrollView.smoothScrollTo(0, binding.editTextSearch.bottom)
                    }
                }
            }

        binding.recyclerViewSearch.layoutManager = LinearLayoutManager(requireContext())
        booksAdapter = BooksAdapter(requireContext(), allBooks) { book ->
            basketViewModel.addToBasket(book)
        }
        binding.recyclerViewSearch.adapter = booksAdapter

        val layoutManager = GridLayoutManager(context, 2, RecyclerView.VERTICAL, false)
        val spaceBetweenItems = resources.getDimensionPixelSize(R.dimen.space_between_items)
        binding.recyclerViewSearch.addItemDecoration(HorizontalSpaceItemDecoration(spaceBetweenItems))
        binding.recyclerViewSearch.layoutManager = layoutManager

        binding.editTextSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                charSequence: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(
                charSequence: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
            }

            override fun afterTextChanged(editable: Editable?) {
                filterBooks(editable.toString())
            }
        })
    }

    private fun filterBooks(query: String) {
        val filteredBooks = allBooks.filter { book ->
            book.title.contains(query, ignoreCase = true)
        }

        booksAdapter.updateItems(filteredBooks)
    }

    private fun loadAllBooks(): List<Book> {
        return listOf(
            Book(getString(R.string.master_and_margarita), 599.99, "https://bidspirit-images.global.ssl.fastly.net/rusenamel/cloned-images/auction_73/lots/import_0/116/1/a_ignore_q_80_w_1000_c_limit_1.jpg"),
            Book(getString(R.string.crime_and_punishment), 449.50, "https://cdn1.ozone.ru/s3/multimedia-k/6717694592.jpg"),
            Book(getString(R.string.war_and_peace), 899.75, "https://nauka.club/wp-content/auploads/914004/roman_tolstogo_voyna_mir.jpg"),
            Book(getString(R.string.pozor), 349.25, "https://trikky.ru/wp-content/blogs.dir/1/files/2021/06/05/579f28c6-fbcd-4997-9973-544e63cbbb7b.jpeg"),
            Book(getString(R.string.little_prince), 299.90, "https://cdn1.ozone.ru/s3/multimedia-q/6053509382.jpg"),
            Book(getString(R.string.mockingbird), 539.80, "https://cdn.img-gorod.ru/nomenclature/26/221/2622192.jpg"),
            Book(getString(R.string.morphine), 1359.99, "https://cdn1.ozone.ru/s3/multimedia-6/6671523750.jpg"),
            Book(getString(R.string.norwegian_forest), 499.00, "https://фантазеры.рф/wa-data/public/shop/products/72/69/36972/images/83027/83027.750x0.jpg"),
            Book(getString(R.string.shame_sun), 699.99, "https://coollib.net/i/96/450496/cover.jpg"),
            Book(getString(R.string.eugene_onegin), 599.99, "https://fkniga.ru/media/product/04/040408/KA-00157697.jpg"),
            Book(getString(R.string.dubrovsky), 849.30, "https://cdn.azbooka.ru/cv/w1100/dd29233e-f2dc-4259-99ba-adf55d1b6022.jpg"),
            Book(getString(R.string.anna_karenina), 199.99, "https://cdn1.ozone.ru/s3/multimedia-s/6537225760.jpg"),
            Book(getString(R.string.karamazov_brothers), 1199.0, "https://fkniga.ru/media/product/04/040404/KA-00057797.jpg"),
            Book(getString(R.string.amityville_horror), 999.99, "https://images-s.kinorium.com/movie/1080/2624083.jpg?1665875218"),
            Book(getString(R.string.gone_girl), 849.60, "https://www.doostihaa.com/img/uploads/2022/04/The-Exorcism-of-Emily-Rose-2005.jpg"),
            Book(getString(R.string.dreamcatcher), 799.75, "https://cdn.img-gorod.ru/nomenclature/25/025/2502578.jpg"),
            Book(getString(R.string.sixth_sense), 349.25, "https://cv4.litres.ru/pub/c/cover_max1500/67210743.jpg"),
            Book(getString(R.string.harry_potter), 2299.90, "https://images.spasibovsem.ru/catalog/original/audiokniga-garri-potter-i-dary-smerti-dzhoan-rouling-otzyvy-1588089182.jpg")
        )
    }

}
