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
    fun `should store a number of goods`() {
        val stock = Stock()
        stockService.stockIn(stock)
    }
}
