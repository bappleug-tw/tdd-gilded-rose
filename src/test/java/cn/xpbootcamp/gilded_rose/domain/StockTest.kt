package cn.xpbootcamp.gilded_rose.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.lang.IllegalArgumentException
import kotlin.test.assertFailsWith

internal class StockTest {

    @Test
    fun `should have Name, Amount, StockInAt, Quantity, SellIn property as default constructor`() {
        val stock = Stock(
                name = "name",
                amount = 1,
                quality = 30,
                sellIn = 20
        )
        assertThat(stock.name).isEqualTo("name")
        assertThat(stock.amount).isEqualTo(1)
        assertThat(stock.quality).isEqualTo(30)
        assertThat(stock.sellIn).isEqualTo(20)
    }

    @Test
    fun `should throw error when name is empty`() {
        assertFailsWith(IllegalArgumentException::class, "invalid empty name") {
            Stock("", 1, 100, 20)
        }
    }

    @Test
    fun `should throw error when amount is less than 1`() {
        val negativeAmount = -1
        assertFailsWith(IllegalArgumentException::class, "invalid amount $negativeAmount") {
            Stock("name", negativeAmount, 100, 20)
        }

        val zeroAmount = 0
        assertFailsWith(IllegalArgumentException::class, "invalid amount $zeroAmount") {
            Stock("name", zeroAmount, 100, 20)
        }
    }

    @Test
    fun `should throw error when quality is below 0 or above 50`() {
        val negativeQuality = -1
        assertFailsWith(IllegalArgumentException::class, "invalid quality $negativeQuality") {
            Stock("name", 10, negativeQuality, 20)
        }

        val tooBigQuality = 51
        assertFailsWith(IllegalArgumentException::class, "invalid quality $tooBigQuality") {
            Stock("name", 10, tooBigQuality, 20)
        }
    }

    @Test
    fun `should throw error when sellIn is below 1`() {
        val expiredSellIn = 0
        assertFailsWith(IllegalArgumentException::class, "invalid sellIn $expiredSellIn") {
            Stock("name", 10, 100, expiredSellIn)
        }
    }
}
