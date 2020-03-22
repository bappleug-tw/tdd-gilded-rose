package cn.xpbootcamp.gilded_rose.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.*

internal class StorageTest {

    private lateinit var stockService: StockService

    @BeforeEach
    fun init() {
        stockService = StockService()
    }

    @Test
    fun `should be able to stock in`() {
        val stock = Stock("name", 1, Date(), 100, 20)
        stockService.stockIn(stock)
    }

    @Test
    fun `should be able to get all the list of stocks that stocked in`() {
        val stock = Stock("name", 1, Date(), 100, 20)
        stockService.stockIn(stock)
        val stocks = stockService.listAll()
        assertThat(stocks).isEqualTo(listOf(stock))
    }
}
