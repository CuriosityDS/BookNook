package ru.mggtk.booknook.Fragments.ShopFragments.Products

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import ru.mggtk.booknook.Adapters.BooksAdapter
import ru.mggtk.booknook.ViewModels.BasketViewModel
import ru.mggtk.booknook.databinding.FragmentProductsHomeBinding
import ru.mggtk.booknook.dataclass.Book

class ProductsHomeFragment : Fragment() {

    private lateinit var binding: FragmentProductsHomeBinding
    private lateinit var booksAdapter: BooksAdapter
    private lateinit var basketViewModel: BasketViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductsHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val books = loadBooks()

        basketViewModel = ViewModelProvider(requireActivity()).get(BasketViewModel::class.java)

        booksAdapter = BooksAdapter(books) { book ->
            basketViewModel.addToBasket(book)
            // Другая логика, если необходимо
        }

        binding.itemsList.layoutManager = LinearLayoutManager(requireContext())
        binding.itemsList.adapter = booksAdapter
    }

    private fun loadBooks(): List<Book> {
        // Временный пример:
        return listOf(
            Book("Мастер и Маргарита", 599.99, "https://bidspirit-images.global.ssl.fastly.net/rusenamel/cloned-images/auction_73/lots/import_0/116/1/a_ignore_q_80_w_1000_c_limit_1.jpg"),
            Book("Преступление и наказание", 449.50, "https://cdn1.ozone.ru/s3/multimedia-k/6717694592.jpg"),
            Book("Война и мир", 899.75, "https://nauka.club/wp-content/auploads/914004/roman_tolstogo_voyna_mir.jpg"),
            Book("1984", 349.25, "https://trikky.ru/wp-content/blogs.dir/1/files/2021/06/05/579f28c6-fbcd-4997-9973-544e63cbbb7b.jpeg"),
            Book("Маленький принц", 299.90, "https://cdn1.ozone.ru/s3/multimedia-q/6053509382.jpg"),
            Book("Убить пересмешника", 539.80, "https://cdn.img-gorod.ru/nomenclature/26/221/2622192.jpg"),
            Book("Морфий", 1359.99, "https://cdn1.ozone.ru/s3/multimedia-6/6671523750.jpg"),
            Book("Норвежский лес", 499.00, "https://фантазеры.рф/wa-data/public/shop/products/72/69/36972/images/83027/83027.750x0.jpg"),
            Book("Позор Солнца", 699.99, "https://coollib.net/i/96/450496/cover.jpg")
        )
    }

}
