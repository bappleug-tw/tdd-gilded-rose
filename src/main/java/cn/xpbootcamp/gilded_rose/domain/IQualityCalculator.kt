package cn.xpbootcamp.gilded_rose.domain

import java.time.LocalDate

interface IQualityCalculator {
    fun currentQuality(good: Good, stockInAt: LocalDate, stockInQuality: Long, sellIn: Long): Long
}
