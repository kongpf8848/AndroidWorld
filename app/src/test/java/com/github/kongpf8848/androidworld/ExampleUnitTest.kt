package com.github.kongpf8848.androidworld

import androidx.core.util.Pools
import org.junit.Assert.*
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        val pools = Pools.SynchronizedPool<String>(10);
        val obj = pools.acquire()
        try {
            println(obj)
        } finally {
            if(obj!=null) {
                pools.release(obj);
            }
        }

    }
}