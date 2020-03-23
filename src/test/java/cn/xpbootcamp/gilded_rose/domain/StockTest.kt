package cn.xpbootcamp.gilded_rose.domain

import cn.xpbootcamp.gilded_rose.mockStock
import cn.xpbootcamp.gilded_rose.stockFrom
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
        val negativeQuality = -1
        assertFailWithMsg(IllegalArgumentException::class, "invalid quality $negativeQuality") {
            mockStock.copy(quality = negativeQuality)
        }

        val tooBigQuality = 51
        assertFailWithMsg(IllegalArgumentException::class, "invalid quality $tooBigQuality") {
            mockStock.copy(quality = tooBigQuality)
        }
    }

    @Test
    fun `should throw error when sellIn is below 1`() {
        val expiredSellIn = 0
        assertFailWithMsg(IllegalArgumentException::class, "invalid sellIn $expiredSellIn") {
            mockStock.copy(sellIn = expiredSellIn)
        }
    }

    @Nested
    inner class CurrentQualityTest {

        @Nested
        inner class CommonRules {
            @Test
            fun `should return current quality eqs to the stock in quality at the first day`() {
                val stock = mockStock.copy(stockInAt = LocalDate.now())
                assertThat(stock.currentQuality).isEqualTo(stock.quality)
            }

            @Test
            fun `should return current quality eqs to the stock in quality - 1 * days passed`() {
                val stockFromYesterday = stockFrom(2)
                assertThat(stockFromYesterday.currentQuality).isEqualTo(stockFromYesterday.quality - 2)

                val stockFrom10DaysAgo = stockFrom(10)
                assertThat(stockFrom10DaysAgo.currentQuality).isEqualTo(stockFrom10DaysAgo.quality - 10)
            }
        }
    }
}
