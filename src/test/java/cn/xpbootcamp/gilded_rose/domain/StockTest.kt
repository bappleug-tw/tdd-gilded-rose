package cn.xpbootcamp.gilded_rose.domain

import cn.xpbootcamp.gilded_rose.*
import cn.xpbootcamp.gilded_rose.utils.assertFailWithMsg
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.lang.IllegalArgumentException
import java.time.LocalDate

internal class StockTest {

    @Test
    fun `should have Name, Amount, StockInAt, Quantity, SellIn property as default constructor`() {
        val stock = Stock(
                good = Good.AGED_BRIE,
                amount = 1,
                quality = 30,
                sellIn = 20,
                stockInAt = LocalDate.now()
        )
        assertThat(stock.good).isEqualTo(Good.AGED_BRIE)
        assertThat(stock.amount).isEqualTo(1)
        assertThat(stock.quality).isEqualTo(30)
        assertThat(stock.sellIn).isEqualTo(20)
    }

    @Test
    fun `should throw error when amount is less than 1`() {
        val negativeAmount = -1
        assertFailWithMsg(IllegalArgumentException::class, "invalid amount $negativeAmount") {
            mockStock.copy(amount = negativeAmount)
        }

        val zeroAmount = 0
        assertFailWithMsg(IllegalArgumentException::class, "invalid amount $zeroAmount") {
            mockStock.copy(amount = zeroAmount)
        }
    }

    @Test
    fun `should throw error when quality is below 0 or above 50`() {
        val negativeQuality = -1L
        assertFailWithMsg(IllegalArgumentException::class, "invalid quality $negativeQuality") {
            mockStock.copy(quality = negativeQuality)
        }

        val tooBigQuality = 51L
        assertFailWithMsg(IllegalArgumentException::class, "invalid quality $tooBigQuality") {
            mockStock.copy(quality = tooBigQuality)
        }
    }

    @Test
    fun `should throw error when sellIn is below 1`() {
        val expiredSellIn = 0L
        assertFailWithMsg(IllegalArgumentException::class, "invalid sellIn $expiredSellIn") {
            mockStock.copy(sellIn = expiredSellIn)
        }
    }

    @Nested
    inner class CurrentQualityTest {

        @Nested
        inner class CommonRules {
            @Test
            fun `should return current quality eqs to the stockInQuality - dailyDepreciationRate * daysAfterStock`() {
                val freshStockToday = mockStock.of(Good.OTHER)
                        .withQuality(30)
                        .beenStockedFor(0)
                assertThat(freshStockToday.currentQuality).isEqualTo(30)

                val stockFrom5DaysAgo = mockStock.of(Good.OTHER)
                        .withQuality(30)
                        .beenStockedFor(5)
                        .expireIn(10)
                assertThat(stockFrom5DaysAgo.currentQuality).isEqualTo(25)

                val stockExpireTomorrow = mockStock.of(Good.OTHER)
                        .withQuality(30)
                        .beenStockedFor(10)
                        .expireIn(10)
                assertThat(stockExpireTomorrow.currentQuality).isEqualTo(20)
            }

            @Test
            fun `should return current quality eqs to the stock in quality - 1 * days after expire`() {
                val stockFromYesterday = mockStock.of(Good.OTHER)
                        .withQuality(30)
                        .beenStockedFor(12)
                        .expireIn(10)
                assertThat(stockFromYesterday.currentQuality).isEqualTo(16)

                val stockFrom10DaysAgo = mockStock.of(Good.OTHER).beenStockedFor(20).expireIn(10)
                assertThat(stockFrom10DaysAgo.currentQuality).isEqualTo(0)
            }

            @Test
            fun `should return current quality never below 0`() {
                val stockFromYesterday = mockStock.of(Good.OTHER)
                        .withQuality(30)
                        .beenStockedFor(500)
                        .expireIn(100)
                assertThat(stockFromYesterday.currentQuality).isEqualTo(0)
            }
        }

        @Nested
        inner class AgedBrieRules {
            @Test
            fun `should return current quality eqs to the stockInQuality + dailyAccretionRate * daysAfterStock`() {
                val freshStockToday = mockStock.of(Good.AGED_BRIE)
                        .withQuality(30)
                        .beenStockedFor(0)
                assertThat(freshStockToday.currentQuality).isEqualTo(freshStockToday.quality)

                val stockFrom5DaysAgo = mockStock.of(Good.AGED_BRIE)
                        .withQuality(30)
                        .beenStockedFor(5)
                        .expireIn(10)
                assertThat(stockFrom5DaysAgo.currentQuality).isEqualTo(35)

                val stockExpireTomorrow = mockStock.of(Good.AGED_BRIE)
                        .withQuality(30)
                        .beenStockedFor(10)
                        .expireIn(10)
                assertThat(stockExpireTomorrow.currentQuality).isEqualTo(40)
            }

            @Test
            fun `quality should never accretion above 50`() {
                val freshStockToday = mockStock.of(Good.AGED_BRIE)
                        .withQuality(30)
                        .beenStockedFor(100_000)
                assertThat(freshStockToday.currentQuality).isEqualTo(50)
            }
        }

        @Nested
        inner class SulfurasRules {
            @Test
            fun `current quality should be always equal to stock in quality`() {
                val freshStockToday = mockStock.of(Good.SULFURAS)
                        .withQuality(30)
                        .beenStockedFor(0)
                assertThat(freshStockToday.currentQuality).isEqualTo(30)

                val stockFrom5DaysAgo = mockStock.of(Good.SULFURAS)
                        .withQuality(30)
                        .beenStockedFor(10)
                        .expireIn(10)
                assertThat(stockFrom5DaysAgo.currentQuality).isEqualTo(30)

                val stockExpireTomorrow = mockStock.of(Good.SULFURAS)
                        .withQuality(30)
                        .beenStockedFor(100_000)
                        .expireIn(10)
                assertThat(stockExpireTomorrow.currentQuality).isEqualTo(30)
            }
        }
    }
}
