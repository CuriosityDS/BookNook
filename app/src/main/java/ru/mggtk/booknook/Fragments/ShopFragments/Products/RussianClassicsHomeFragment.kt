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
import ru.mggtk.booknook.databinding.FragmentRussianClassicsHomeBinding
import ru.mggtk.booknook.databinding.FragmentSpecialOfferHomeBinding
import ru.mggtk.booknook.dataclass.Book

class RussianClassicsHomeFragment : Fragment() {

    private lateinit var binding: FragmentRussianClassicsHomeBinding
    private lateinit var booksAdapter: BooksAdapter
    private lateinit var basketViewModel: BasketViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRussianClassicsHomeBinding.inflate(inflater, container, false)
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
            Book("Евгений Онегин", 599.99, "https://fkniga.ru/media/product/04/040408/KA-00157697.jpg"),
            Book("Дубровский", 849.30, "https://cdn.azbooka.ru/cv/w1100/dd29233e-f2dc-4259-99ba-adf55d1b6022.jpg"),
            Book("Мастер и Маргарита", 599.2, "https://bidspirit-images.global.ssl.fastly.net/rusenamel/cloned-images/auction_73/lots/import_0/116/1/a_ignore_q_80_w_1000_c_limit_1.jpg"),
            Book("Преступление и наказание", 449.50, "https://cdn1.ozone.ru/s3/multimedia-k/6717694592.jpg"),
            Book("Война и мир", 899.75, "https://nauka.club/wp-content/auploads/914004/roman_tolstogo_voyna_mir.jpg"),
            Book("Анна Каренина", 199.99, "https://cdn1.ozone.ru/s3/multimedia-s/6537225760.jpg"),
            Book("Братья Карамазовы", 1199.0, "https://fkniga.ru/media/product/04/040404/KA-00057797.jpg")
        )
    }

}