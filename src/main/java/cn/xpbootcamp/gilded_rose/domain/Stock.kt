package cn.xpbootcamp.gilded_rose.domain

import java.util.*

data class Stock(
        val name: String,
        val amount: Int,
        val stockInAt: Date,
        val quantity: Int,
        val sellIn: Int
)
