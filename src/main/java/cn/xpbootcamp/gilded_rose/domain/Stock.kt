package cn.xpbootcamp.gilded_rose.domain

import cn.xpbootcamp.gilded_rose.domain.Good.*
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

    var qualityCalculator: IQualityCalculator = when (good) {
        AGED_BRIE -> AgedBrieQualityCalculator()
        SULFURAS -> SulfurasQualityCalculator()
        BACKSTAGE_PASS -> TODO()
        OTHER -> CommonQualityCalculator()
    }

    init {
        if (amount < 1) throw IllegalArgumentException("invalid amount $amount")
        if (quality < 0 || quality > 50) throw IllegalArgumentException("invalid quality $quality")
        if (sellIn < 1) throw IllegalArgumentException("invalid sellIn $sellIn")
    }
}
