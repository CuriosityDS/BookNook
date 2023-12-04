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
            Book("Мастер и Маргарита", 599.99, "https://via.placeholder.com/150"),
            Book("Преступление и наказание", 449.50, "https://via.placeholder.com/150"),
            Book("Война и мир", 899.75, "https://via.placeholder.com/150"),
            Book("1984", 349.25, "https://via.placeholder.com/150"),
            Book("Позор Кремля", 1699.99, "https://via.placeholder.com/150"),
            Book("Маленький принц", 299.90, "https://via.placeholder.com/150"),
            Book("Убить пересмешника", 539.80, "https://via.placeholder.com/150"),
            Book("Мастер йоги", 189.60, "https://via.placeholder.com/150"),
            Book("Морфий", 1359.99, "https://via.placeholder.com/150"),
            Book("Норвежский лес", 499.00, "https://via.placeholder.com/150"),
            Book("Позор Солнца", 699.99, "https://via.placeholder.com/150")
        )
    }

}
