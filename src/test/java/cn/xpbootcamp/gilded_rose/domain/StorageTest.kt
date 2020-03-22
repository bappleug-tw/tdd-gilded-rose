package cn.xpbootcamp.gilded_rose.domain

import cn.xpbootcamp.gilded_rose.mockStock
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class StorageTest {

    private lateinit var stockService: StockService

    @BeforeEach
    fun init() {
        stockService = StockService()
    }

    @Test
    fun `should be able to stock in`() {
        val stock = stockService.stockIn(mockStock)
        assertThat(stock.stockInAt).isNotNull()
    }

    @Test
    fun `should be able to get all the list of stocks that stocked in`() {
        stockService.stockIn(mockStock)
        val stocks = stockService.listAll()
        assertThat(stocks).isEqualTo(listOf(mockStock))
    }
}
