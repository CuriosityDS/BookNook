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
        _basketItems.value = currentItems + book
    }

    fun clearBasket() {
        _basketItems.value = emptyList()
    }
    fun removeFromBasket(book: Book) {
        // Получаем текущий список товаров из корзины
        val currentBasket = _basketItems.value.orEmpty().toMutableList()

        // Удаляем выбранный товар
        currentBasket.remove(book)

        // Обновляем LiveData, чтобы уведомить наблюдателей об изменении
        _basketItems.value = currentBasket
    }
}
