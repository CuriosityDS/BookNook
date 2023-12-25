package ru.mggtk.booknook.Fragments.ShopFragments.Products

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import ru.mggtk.booknook.Adapters.BooksAdapter
import ru.mggtk.booknook.R
import ru.mggtk.booknook.ViewModels.BasketViewModel
import ru.mggtk.booknook.databinding.FragmentProductsHomeBinding
import ru.mggtk.booknook.databinding.FragmentSpecialOfferHomeBinding
import ru.mggtk.booknook.dataclass.Book

class SpecialOfferHomeFragment : Fragment() {

    private lateinit var binding: FragmentSpecialOfferHomeBinding
    private lateinit var booksAdapter: BooksAdapter
    private lateinit var basketViewModel: BasketViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSpecialOfferHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val books = loadRandomBooks()

        basketViewModel = ViewModelProvider(requireActivity()).get(BasketViewModel::class.java)

        booksAdapter = BooksAdapter(books) { book ->
            basketViewModel.addToBasket(book)
        }

        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.itemsList.layoutManager = layoutManager
        binding.itemsList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val spaceBetweenItems = resources.getDimensionPixelSize(R.dimen.space_between_items)
        binding.itemsList.addItemDecoration(HorizontalSpaceItemDecoration(spaceBetweenItems))
        binding.itemsList.adapter = booksAdapter
    }

    private fun loadRandomBooks(): List<Book> {
        val allBooks = loadBooks()
        return allBooks.shuffled().take(5)
    }

    private fun loadBooks(): List<Book> {
        // Временный пример:
        return listOf(
            Book("Амитивилльское зло", 999.99, "https://images-s.kinorium.com/movie/1080/2624083.jpg?1665875218"),
            Book("Исчезновение Эмили Роуз", 849.60, "https://www.doostihaa.com/img/uploads/2022/04/The-Exorcism-of-Emily-Rose-2005.jpg"),
            Book("Ловец снов", 799.75, "https://cdn.img-gorod.ru/nomenclature/25/025/2502578.jpg"),
            Book("Шестое чувство", 349.25, "https://cv4.litres.ru/pub/c/cover_max1500/67210743.jpg"),
            Book("Гарри Поттер и Дары Смерти", 2299.90, "https://images.spasibovsem.ru/catalog/original/audiokniga-garri-potter-i-dary-smerti-dzhoan-rouling-otzyvy-1588089182.jpg"),
        )
    }

}