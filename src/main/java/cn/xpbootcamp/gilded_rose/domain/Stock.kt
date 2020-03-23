package cn.xpbootcamp.gilded_rose.domain

import java.lang.IllegalArgumentException
import java.time.LocalDate

data class Stock(
        val good: Good,
        val amount: Int,
        val quality: Long,
        val sellIn: Long,
        val stockInAt: LocalDate
) {

    val currentQuality: Long
        get() = qualityCalculator.currentQuality(good, stockInAt, quality, sellIn)

    var qualityCalculator: QualityCalculator = QualityCalculator()

    init {
        if (amount < 1) throw IllegalArgumentException("invalid amount $amount")
        if (quality < 0 || quality > 50) throw IllegalArgumentException("invalid quality $quality")
        if (sellIn < 1) throw IllegalArgumentException("invalid sellIn $sellIn")
    }
}

object COMMON_DAILY_DEPRECIATION_RATE {
    const val BEFORE_EXPIRE = 1
    const val AFTER_EXPIRE = BEFORE_EXPIRE * 2
}

object AGED_BRIE_DAILY_DEPRECIATION_RATE {
    const val BEFORE_EXPIRE = -1
    const val AFTER_EXPIRE = -1
}
