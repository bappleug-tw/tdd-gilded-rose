package cn.xpbootcamp.gilded_rose

import cn.xpbootcamp.gilded_rose.domain.Good
import cn.xpbootcamp.gilded_rose.domain.Stock
import java.time.LocalDate

val mockStock = Stock(Good.OTHER, 1, 30, 20, LocalDate.now())

fun Stock.of(good: Good) = this.copy(good = good)
fun Stock.withQuality(quality: Long) = this.copy(qualityAtStockIn = quality)
fun Stock.expireIn(sellIn: Long) = this.copy(sellInAtStockIn = sellIn)
fun Stock.beenStockedFor(days: Long) = this.copy(stockInAt = LocalDate.now().minusDays(days))
