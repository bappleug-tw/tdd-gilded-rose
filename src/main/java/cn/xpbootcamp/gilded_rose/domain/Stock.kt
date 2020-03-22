package cn.xpbootcamp.gilded_rose.domain

import java.lang.IllegalArgumentException
import java.util.*

data class Stock(
        val name: String,
        val amount: Int,
        val stockInAt: Date,
        val quantity: Int,
        val sellIn: Int
) {
        init {
                if (name.isEmpty()) throw IllegalArgumentException("invalid empty name")
                if (amount < 1) throw IllegalArgumentException("invalid amount $$amount")
                if (quantity < 0 || quantity > 50) throw IllegalArgumentException("invalid quantity $quantity")
        }
}
