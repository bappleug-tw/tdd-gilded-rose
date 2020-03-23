package cn.xpbootcamp.gilded_rose

import cn.xpbootcamp.gilded_rose.domain.Good
import cn.xpbootcamp.gilded_rose.domain.Stock
import java.time.LocalDate

val mockStock = Stock(Good.AGED_BRIE, 1, 30, 20, LocalDate.now())

fun stockFrom(daysAgo: Long): Stock {
    return mockStock.copy(stockInAt = LocalDate.now().minusDays(daysAgo))
}
