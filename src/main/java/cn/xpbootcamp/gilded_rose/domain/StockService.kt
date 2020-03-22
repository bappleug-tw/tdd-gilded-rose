package cn.xpbootcamp.gilded_rose.domain

class StockService {

    private val stocks: MutableList<Stock> = mutableListOf()

    fun stockIn(stock: Stock): Unit {
        stocks.add(stock)
    }

    fun listAll(): List<Stock> {
        return stocks
    }

}
