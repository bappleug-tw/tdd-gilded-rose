package cn.xpbootcamp.gilded_rose.domain

import java.time.LocalDate
import java.time.temporal.ChronoUnit
import kotlin.math.max
import kotlin.math.min

class QualityCalculator : IQualityCalculator {
    fun currentQuality(good: Good, stockInAt: LocalDate, stockInQuality: Long, sellIn: Long): Long {
        val stockedDays = stockInAt.until(LocalDate.now(), ChronoUnit.DAYS)
        return when (good) {
            Good.AGED_BRIE -> min(50, stockInQuality - stockedDays * AGED_BRIE_DAILY_DEPRECIATION_RATE.BEFORE_EXPIRE)
            Good.SULFURAS -> stockInQuality
            else -> max(0, if (stockedDays < sellIn) {
                stockInQuality - stockedDays * COMMON_DAILY_DEPRECIATION_RATE.BEFORE_EXPIRE
            } else {
                stockInQuality - sellIn * COMMON_DAILY_DEPRECIATION_RATE.BEFORE_EXPIRE - (stockedDays - sellIn) * COMMON_DAILY_DEPRECIATION_RATE.AFTER_EXPIRE
            })
        }
    }
}
