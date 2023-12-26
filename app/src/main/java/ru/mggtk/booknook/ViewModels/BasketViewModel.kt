package ru.mggtk.booknook.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.mggtk.booknook.dataclass.Book

class BasketViewModel : ViewModel() {

    private val _basketItems = MutableLiveData<List<Book>>()
    val basketItems: LiveData<List<Book>> get() = _basketItems

    init {
        _basketItems.value = emptyList()
    }

    fun addToBasket(book: Book) {
        val currentItems = _basketItems.value ?: emptyList()
        val existingBook = currentItems.find { it.title == book.title }

        if (existingBook != null) {
            existingBook.quantity += 1
        } else {
            val newBook = book.copy(quantity = 1)
            _basketItems.value = currentItems + newBook
        }

        calculateRoundedPrices()
    }


    fun decreaseQuantity(book: Book)  {
        // Получаем текущий список товаров из корзины
        val currentBasket = _basketItems.value.orEmpty().toMutableList()

        // Находим выбранный товар в корзине
        val existingBook = currentBasket.find { it.title == book.title }

        // Если товар присутствует в корзине
        if (existingBook != null) {
            // Уменьшаем количество товара
            existingBook.quantity -= 1

            // Если количество стало 0, удаляем товар из корзины
            if (existingBook.quantity == 0) {
                currentBasket.remove(existingBook)
            }

            // Обновляем LiveData, чтобы уведомить наблюдателей об изменении
            _basketItems.value = currentBasket
        }
        calculateRoundedPrices()
    }

    fun removeFromBasket(book: Book) {
        val currentItems = _basketItems.value ?: emptyList()
        _basketItems.value = currentItems.filter { it.title != book.title }
        calculateRoundedPrices()
    }

    fun clearBasket() {
        _basketItems.value = emptyList()
    }

    private fun calculateRoundedPrices() {
        _basketItems.value?.forEach { it.calculateRoundedPrice() }
        _basketItems.value = _basketItems.value // Уведомляем об изменении данных
    }


}
