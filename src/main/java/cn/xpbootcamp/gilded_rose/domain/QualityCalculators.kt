package cn.xpbootcamp.gilded_rose.domain

import java.time.LocalDate
import java.time.temporal.ChronoUnit
import kotlin.math.max
import kotlin.math.min

class AgedBrieQualityCalculator : IQualityCalculator {
    companion object {
        const val AGED_BRIE_DAILY_ACCRETION_RATE = 1
    }

    override fun currentQuality(good: Good, stockInAt: LocalDate, stockInQuality: Long, sellIn: Long): Long {
        val stockedDays = stockInAt.until(LocalDate.now(), ChronoUnit.DAYS)
        return min(50, stockInQuality + stockedDays * AGED_BRIE_DAILY_ACCRETION_RATE)
    }
}

class SulfurasQualityCalculator : IQualityCalculator {
    override fun currentQuality(good: Good, stockInAt: LocalDate, stockInQuality: Long, sellIn: Long): Long {
        return stockInQuality
    }
}

class CommonQualityCalculator : IQualityCalculator {
    companion object {
        const val DAILY_DEPRECIATION_RATE_BEFORE_EXPIRE = 1
        const val DAILY_DEPRECIATION_RATE_AFTER_EXPIRE = DAILY_DEPRECIATION_RATE_BEFORE_EXPIRE * 2
    }

    override fun currentQuality(good: Good, stockInAt: LocalDate, stockInQuality: Long, sellIn: Long): Long {
        val stockedDays = stockInAt.until(LocalDate.now(), ChronoUnit.DAYS)
        return max(0, if (stockedDays < sellIn) {
            stockInQuality - stockedDays * DAILY_DEPRECIATION_RATE_BEFORE_EXPIRE
        } else {
            stockInQuality - sellIn * DAILY_DEPRECIATION_RATE_BEFORE_EXPIRE - (stockedDays - sellIn) * DAILY_DEPRECIATION_RATE_AFTER_EXPIRE
        })
    }
}
