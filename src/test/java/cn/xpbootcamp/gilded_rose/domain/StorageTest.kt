package cn.xpbootcamp.gilded_rose.domain

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class StorageTest {

    lateinit var stockService: StockService

    @BeforeEach
    fun init() {
        stockService = StockService()
    }

    @Test
    fun `should be able to stock in`() {
        val stock = Stock()
        stockService.stockIn(stock)
    }

    @Test
    fun `should be able to get list of stocks`() {
        val stocks = stockService.listAll()
    }
}
