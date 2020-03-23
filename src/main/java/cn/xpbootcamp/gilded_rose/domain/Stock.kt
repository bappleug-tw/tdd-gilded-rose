package cn.xpbootcamp.gilded_rose.domain

import java.lang.IllegalArgumentException
import java.time.LocalDate
import java.time.temporal.ChronoUnit.*
import kotlin.math.max

data class Stock(
        val good: Good,
        val amount: Int,
        val quality: Long,
        val sellIn: Long,
        val stockInAt: LocalDate
) {

    val currentQuality: Long
        get() {
            val expiredDays = max(0, stockInAt.plusDays(sellIn).until(LocalDate.now(), DAYS))
            return max(quality - expiredDays * 1, 0)
        }

    init {
        if (amount < 1) throw IllegalArgumentException("invalid amount $amount")
        if (quality < 0 || quality > 50) throw IllegalArgumentException("invalid quality $quality")
        if (sellIn < 1) throw IllegalArgumentException("invalid sellIn $sellIn")
    }
}
