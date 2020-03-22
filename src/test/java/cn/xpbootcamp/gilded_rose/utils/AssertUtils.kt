package cn.xpbootcamp.gilded_rose.utils

import org.assertj.core.api.Assertions.assertThat
import kotlin.reflect.KClass
import kotlin.test.assertFailsWith

fun <T: Throwable> assertFailWithMsg(kClass: KClass<T>, msg: String, block: () -> Unit): T {
    val throwable = assertFailsWith(kClass, block)
    assertThat(throwable).hasMessage(msg)
    return throwable
}
