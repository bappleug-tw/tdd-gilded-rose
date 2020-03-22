package cn.xpbootcamp.gilded_rose.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.*

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
}
