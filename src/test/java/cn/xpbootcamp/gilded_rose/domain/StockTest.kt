package cn.xpbootcamp.gilded_rose.domain

import cn.xpbootcamp.gilded_rose.utils.assertFailWithMsg
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.lang.IllegalArgumentException

internal class StockTest {

    @Test
    fun `should have Name, Amount, StockInAt, Quantity, SellIn property as default constructor`() {
        val stock = Stock(
                good = Good.AGED_BRIE,
                amount = 1,
                quality = 30,
                sellIn = 20
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
            Stock(Good.AGED_BRIE, negativeAmount, 100, 20)
        }

        val zeroAmount = 0
        assertFailWithMsg(IllegalArgumentException::class, "invalid amount $zeroAmount") {
            Stock(Good.AGED_BRIE, zeroAmount, 100, 20)
        }
    }

    @Test
    fun `should throw error when quality is below 0 or above 50`() {
        val negativeQuality = -1
        assertFailWithMsg(IllegalArgumentException::class, "invalid quality $negativeQuality") {
            Stock(Good.AGED_BRIE, 10, negativeQuality, 20)
        }

        val tooBigQuality = 51
        assertFailWithMsg(IllegalArgumentException::class, "invalid quality $tooBigQuality") {
            Stock(Good.AGED_BRIE, 10, tooBigQuality, 20)
        }
    }

    @Test
    fun `should throw error when sellIn is below 1`() {
        val expiredSellIn = 0
        assertFailWithMsg(IllegalArgumentException::class, "invalid sellIn $expiredSellIn") {
            Stock(Good.AGED_BRIE, 10, 100, expiredSellIn)
        }
    }
}
