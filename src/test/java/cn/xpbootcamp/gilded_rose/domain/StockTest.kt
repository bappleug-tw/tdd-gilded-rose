package cn.xpbootcamp.gilded_rose.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Test
import java.lang.IllegalArgumentException
import java.util.*
import kotlin.test.assertFailsWith

internal class StockTest {

    @Test
    fun `should have Name, Amount, StockInAt, Quantity, SellIn property`() {
        val stockInDate = Date()
        val stock = Stock(
                name = "name",
                amount = 1,
                stockInAt = stockInDate,
                quantity = 100,
                sellIn = 20
        )
        assertThat(stock.name).isEqualTo("name")
        assertThat(stock.amount).isEqualTo(1)
        assertThat(stock.stockInAt).isEqualTo(stockInDate)
        assertThat(stock.quantity).isEqualTo(100)
        assertThat(stock.sellIn).isEqualTo(20)
    }

    @Test
    fun `should throw error when amount less than 1`() {
        val negativeAmount = -1
        assertFailsWith(IllegalArgumentException::class, "invalid amount $negativeAmount") {
            Stock("name", negativeAmount, Date(), 100, 20)
        }

        val zeroAmount = 0
        assertFailsWith(IllegalArgumentException::class, "invalid amount $zeroAmount") {
            Stock("name", zeroAmount, Date(), 100, 20)
        }
    }
}
