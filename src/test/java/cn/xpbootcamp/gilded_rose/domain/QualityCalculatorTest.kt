package cn.xpbootcamp.gilded_rose.domain

import cn.xpbootcamp.gilded_rose.*
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class QualityCalculatorTest {

    @Nested
    inner class CommonGoods {
        @Test
        fun `should return current quality eqs to the stockInQuality - dailyDepreciationRate * daysAfterStock`() {
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
        fun `should return current quality eqs to the stock in quality - 1 * days after expire`() {
            val stockFromYesterday = mockStock.of(Good.OTHER)
                    .withQuality(30)
                    .beenStockedFor(12)
                    .expireIn(10)
            Assertions.assertThat(stockFromYesterday.quality).isEqualTo(16)

            val stockFrom10DaysAgo = mockStock.of(Good.OTHER).beenStockedFor(20).expireIn(10)
            Assertions.assertThat(stockFrom10DaysAgo.quality).isEqualTo(0)
        }

        @Test
        fun `should return current quality never below 0`() {
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
        fun `should return current quality eqs to the stockInQuality + dailyAccretionRate * daysAfterStock`() {
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
        fun `current quality should be always equal to stock in quality`() {
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
        fun `current quality should be always equal to stock in quality`() {
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
}
