package com.example.jetpackdemeo.test

import android.content.Context
import com.google.common.hash.BloomFilter
import com.google.common.hash.Funnels
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream

object BloomFilterUtil {
    fun generateBloomFilter(list: List<String>): BloomFilter<String> {
        // 此处精度和ios保持一致，ios默认是1.0/(size + 1)
        val bloomFilter: BloomFilter<String> = BloomFilter.create(
            Funnels.stringFunnel(Charsets.UTF_8),
            list.size,
            1.0 / (list.size + 1)
        )

        list.forEach {
            bloomFilter.put(it)
        }

        return bloomFilter
    }

    fun writeBloomFilterToSp(
        bloomFilter: BloomFilter<String>?,
        context: Context,
        fileName: String,
        valueName: String
    ) {
//        val key = HexUtils.toHexFormat("https://bilibili.com/huiyuangou/goods/detail.html?name=2233https://bilibili.com/huiyuangou/goods/detail.html?name=2233")
        val key =
            "https://bilibili.com/huiyuangou/goods/detail.html?name=2233https://bilibili.com/huiyuangou/goods/detail.html?name=2233"
        bloomFilter?.apply {
            try {
                val outputStream = ByteArrayOutputStream()
                bloomFilter.writeTo(outputStream)

                // 注意，此处需要使用Charsets.ISO_8859_1
                val string = String(outputStream.toByteArray(), Charsets.ISO_8859_1)

                context.getSharedPreferences(fileName, Context.MODE_PRIVATE).edit()
                    .putString(key, string).apply()

                outputStream.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun readBloomFilterFromSp(
        context: Context,
        fileName: String,
        valueName: String
    ): BloomFilter<String>? {
//        val key =
//            HexUtils.toHexFormat("https://bilibili.com/huiyuangou/goods/detail.html?name=2233https://bilibili.com/huiyuangou/goods/detail.html?name=2233")

        val key =
            "https://bilibili.com/huiyuangou/goods/detail.html?name=2233https://bilibili.com/huiyuangou/goods/detail.html?name=2233"

        return context.getSharedPreferences(fileName, Context.MODE_PRIVATE).getString(key, "")
            ?.run {
                // 注意，此处需要使用Charsets.ISO_8859_1
                BloomFilter.readFrom(
                    ByteArrayInputStream(toByteArray(Charsets.ISO_8859_1)),
                    Funnels.stringFunnel(Charsets.UTF_8)
                )
            }
    }
}