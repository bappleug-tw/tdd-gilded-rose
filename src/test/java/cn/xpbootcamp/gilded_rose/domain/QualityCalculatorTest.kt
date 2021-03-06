package cn.xpbootcamp.gilded_rose.domain

import cn.xpbootcamp.gilded_rose.utils.*
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class QualityCalculatorTest {

    @Nested
    inner class CommonGoods {
        @Test
        fun `should return quality eqs to the stockInQuality - dailyDepreciationRate * daysAfterStock`() {
            val freshStockToday = mockStock.of(Good.OTHER)
                    .withQuality(30)
                    .beenStockedFor(0)
            Assertions.assertThat(freshStockToday.quality).isEqualTo(30)

            val stockFrom5DaysAgo = mockStock.of(Good.OTHER)
                    .withQuality(30)
                    .beenStockedFor(5)
                    .expireIn(10)
            Assertions.assertThat(stockFrom5DaysAgo.quality).isEqualTo(25)

            val stockExpireTomorrow = mockStock.of(Good.OTHER)
                    .withQuality(30)
                    .beenStockedFor(10)
                    .expireIn(10)
            Assertions.assertThat(stockExpireTomorrow.quality).isEqualTo(20)
        }

        @Test
        fun `should return quality eqs to the stockInQuality - 1 * days after expire`() {
            val stockFromYesterday = mockStock.of(Good.OTHER)
                    .withQuality(30)
                    .beenStockedFor(12)
                    .expireIn(10)
            Assertions.assertThat(stockFromYesterday.quality).isEqualTo(16)

            val stockFrom10DaysAgo = mockStock.of(Good.OTHER).beenStockedFor(20).expireIn(10)
            Assertions.assertThat(stockFrom10DaysAgo.quality).isEqualTo(0)
        }

        @Test
        fun `should return quality never below 0`() {
            val stockFromYesterday = mockStock.of(Good.OTHER)
                    .withQuality(30)
                    .beenStockedFor(500)
                    .expireIn(100)
            Assertions.assertThat(stockFromYesterday.quality).isEqualTo(0)
        }
    }

    @Nested
    inner class AgedBrie {
        @Test
        fun `should return quality eqs to the stockInQuality + dailyAccretionRate * daysAfterStock`() {
            val freshStockToday = mockStock.of(Good.AGED_BRIE)
                    .withQuality(30)
                    .beenStockedFor(0)
            Assertions.assertThat(freshStockToday.quality).isEqualTo(freshStockToday.qualityAtStockIn)

            val stockFrom5DaysAgo = mockStock.of(Good.AGED_BRIE)
                    .withQuality(30)
                    .beenStockedFor(5)
                    .expireIn(10)
            Assertions.assertThat(stockFrom5DaysAgo.quality).isEqualTo(35)

            val stockExpireTomorrow = mockStock.of(Good.AGED_BRIE)
                    .withQuality(30)
                    .beenStockedFor(10)
                    .expireIn(10)
            Assertions.assertThat(stockExpireTomorrow.quality).isEqualTo(40)
        }

        @Test
        fun `quality should never accretion above 50`() {
            val freshStockToday = mockStock.of(Good.AGED_BRIE)
                    .withQuality(30)
                    .beenStockedFor(100_000)
            Assertions.assertThat(freshStockToday.quality).isEqualTo(50)
        }
    }

    @Nested
    inner class Sulfuras {
        @Test
        fun `quality should be always equal to stockInQuality`() {
            val freshStockToday = mockStock.of(Good.SULFURAS)
                    .withQuality(30)
                    .beenStockedFor(0)
            Assertions.assertThat(freshStockToday.quality).isEqualTo(30)

            val stockFrom5DaysAgo = mockStock.of(Good.SULFURAS)
                    .withQuality(30)
                    .beenStockedFor(10)
                    .expireIn(10)
            Assertions.assertThat(stockFrom5DaysAgo.quality).isEqualTo(30)

            val stockExpireTomorrow = mockStock.of(Good.SULFURAS)
                    .withQuality(30)
                    .beenStockedFor(100_000)
                    .expireIn(10)
            Assertions.assertThat(stockExpireTomorrow.quality).isEqualTo(30)
        }
    }

    @Nested
    inner class BackstagePass {
        @Test
        fun `quality should rise at lowest rate per days 10 days before the show`() {
            var stock = mockStock.of(Good.BACKSTAGE_PASS)
                    .withQuality(20)
                    .beenStockedFor(0)
                    .expireIn(30)
            Assertions.assertThat(stock.quality).isEqualTo(20)

            stock = mockStock.of(Good.BACKSTAGE_PASS)
                    .withQuality(20)
                    .beenStockedFor(10)
                    .expireIn(30)
            Assertions.assertThat(stock.quality).isEqualTo(30)

            stock = mockStock.of(Good.BACKSTAGE_PASS)
                    .withQuality(20)
                    .beenStockedFor(20)
                    .expireIn(30)
            Assertions.assertThat(stock.quality).isEqualTo(40)
        }

        @Test
        fun `quality should rise at medium rate per days 5 days before the show`() {
            var stock = mockStock.of(Good.BACKSTAGE_PASS)
                    .withQuality(20)
                    .beenStockedFor(6)
                    .expireIn(15)
            Assertions.assertThat(stock.quality).isEqualTo(27)

            stock = mockStock.of(Good.BACKSTAGE_PASS)
                    .withQuality(20)
                    .beenStockedFor(10)
                    .expireIn(15)
            Assertions.assertThat(stock.quality).isEqualTo(35)
        }

        @Test
        fun `quality should rise at high rate per days within 5 days before the show`() {
            var stock = mockStock.of(Good.BACKSTAGE_PASS)
                    .withQuality(20)
                    .beenStockedFor(11)
                    .expireIn(15)
            Assertions.assertThat(stock.quality).isEqualTo(38)

            stock = mockStock.of(Good.BACKSTAGE_PASS)
                    .withQuality(20)
                    .beenStockedFor(15)
                    .expireIn(15)
            Assertions.assertThat(stock.quality).isEqualTo(50)
        }

        @Test
        fun `quality should never rise higher than 50`() {
            val stock = mockStock.of(Good.BACKSTAGE_PASS)
                    .withQuality(40)
                    .beenStockedFor(15)
                    .expireIn(15)
            Assertions.assertThat(stock.quality).isEqualTo(50)
        }

        @Test
        fun `quality should be 0 after expire`() {
            val stock = mockStock.of(Good.BACKSTAGE_PASS)
                    .withQuality(40)
                    .beenStockedFor(16)
                    .expireIn(15)
            Assertions.assertThat(stock.quality).isEqualTo(0)
        }
    }
}
