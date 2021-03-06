package cn.xpbootcamp.gilded_rose.domain

class StockService {

    private val stocks: MutableList<Stock> = mutableListOf()

    fun stockIn(stock: Stock): Stock {
        stocks.add(stock)
        return stock
    }

    fun listAll(): List<Stock> {
        return stocks
    }

}
