package cn.xpbootcamp.gilded_rose.domain

import cn.xpbootcamp.gilded_rose.domain.Good.*
import java.lang.IllegalArgumentException
import java.time.LocalDate
import java.time.temporal.ChronoUnit

data class Stock(
        val good: Good,
        val amount: Int,
        val qualityAtStockIn: Long,
        val sellInAtStockIn: Long,
        val stockInAt: LocalDate
) {

    val sellIn: Long = sellInAtStockIn - stockInAt.until(LocalDate.now(), ChronoUnit.DAYS)

    val quality: Long
        get() = qualityCalculator.currentQuality(good, stockInAt, qualityAtStockIn, sellInAtStockIn)

    var qualityCalculator: IQualityCalculator = when (good) {
        AGED_BRIE -> AgedBrieQualityCalculator()
        SULFURAS -> SulfurasQualityCalculator()
        BACKSTAGE_PASS -> TODO()
        OTHER -> CommonQualityCalculator()
    }

    init {
        if (amount < 1) throw IllegalArgumentException("invalid amount $amount")
        if (qualityAtStockIn < 0 || qualityAtStockIn > 50) throw IllegalArgumentException("invalid quality $qualityAtStockIn")
        if (sellInAtStockIn < 1) throw IllegalArgumentException("invalid sellIn $sellInAtStockIn")
    }
}
