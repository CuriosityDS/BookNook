package ru.mggtk.booknook.dataclass

import java.math.BigDecimal
import java.math.RoundingMode

data class Book(
    val title: String,
    var price: Double,
    val imageUrl: String,
    var quantity: Int = 1
) {
    var roundedPrice: Double = 0.0
        private set

    init {
        calculateRoundedPrice()
    }

    fun calculateRoundedPrice() {
        roundedPrice = BigDecimal(price * quantity).setScale(2, RoundingMode.HALF_EVEN).toDouble()
    }
}
