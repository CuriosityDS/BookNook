package ru.mggtk.booknook.Fragments.ShopFragments.BasketFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import ru.mggtk.booknook.Adapters.BasketAdapter
import ru.mggtk.booknook.R
import ru.mggtk.booknook.ViewModels.BasketViewModel
import ru.mggtk.booknook.databinding.FragmentBasketFullBinding
import ru.mggtk.booknook.dataclass.Book

class BasketFullFragment : Fragment() {

    private lateinit var basketViewModel: BasketViewModel
    private lateinit var binding: FragmentBasketFullBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBasketFullBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        basketViewModel = ViewModelProvider(requireActivity()).get(BasketViewModel::class.java)

        val adapter = BasketAdapter(
            requireContext(),
            basketViewModel.basketItems.value ?: emptyList(),
            onDeleteItemClickListener = { book -> onDeleteItemClick(book) },
            onIncreaseQuantityClick = { book -> onIncreaseQuantityClick(book) },
            onDecreaseQuantityClick = { book -> onDecreaseQuantityClick(book) }
        )

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        // Обновляем список при изменении данных в корзине
        basketViewModel.basketItems.observe(viewLifecycleOwner, { items ->
            adapter.updateItems(items)
            updateTotalPrice(items)
        })

        // Обработка нажатия кнопки "оплатить"
        binding.payButton.setOnClickListener {
            onPayButtonClick()
        }

        // Обработка нажатия кнопки "удалить товар"
        adapter.setOnDeleteClickListener { book ->
            onDeleteItemClick(book)
        }
    }

    private fun updateTotalPrice(items: List<Book>) {
        val totalPrice = items.sumByDouble { it.price * it.quantity }
        val formattedTotalPrice = String.format("%.2f", totalPrice)
        binding.totalPriceTextView.text = "${getString(R.string.total_cost)}: $formattedTotalPrice руб."
    }

    private fun onPayButtonClick() {
        // Получаем текущую общую стоимость корзины
        val totalCost = basketViewModel.basketItems.value?.sumByDouble { it.price * it.quantity } ?: 0.0

        if (totalCost > 0) {
            // Очищаем корзину
            basketViewModel.clearBasket()

            // Обновляем интерфейс
            binding.totalPriceTextView.text = "${getString(R.string.total_cost)}: 0 руб."

            // Выводим сообщение об успешной оплате
            Toast.makeText(requireContext(), getString(R.string.order_paid), Toast.LENGTH_SHORT).show()

            // Закрываем фрагмент после оплаты (если требуется)
            // fragmentManager?.beginTransaction()?.remove(this)?.commit()
        } else {
        }
    }

    private fun onDeleteItemClick(book: Book) {
        // Удаляем товар из корзины
        basketViewModel.removeFromBasket(book)
    }

    private fun onIncreaseQuantityClick(book: Book) {
        // Увеличиваем количество товара в корзине
        basketViewModel.addToBasket(book)
    }

    private fun onDecreaseQuantityClick(book: Book) {
        // Уменьшаем количество товара в корзине
        basketViewModel.decreaseQuantity(book)
    }
}


