package cn.xpbootcamp.gilded_rose.domain

import java.lang.IllegalArgumentException
import java.util.*

data class Stock(
        val name: String,
        val amount: Int,
        val quality: Int,
        val sellIn: Int
) {
        lateinit var stockInAt: Date

        init {
                if (name.isEmpty()) throw IllegalArgumentException("invalid empty name")
                if (amount < 1) throw IllegalArgumentException("invalid amount $$amount")
                if (quality < 0 || quality > 50) throw IllegalArgumentException("invalid quality $quality")
                if (sellIn < 1) throw IllegalArgumentException("invalid sellIn $sellIn")
        }
}
