package com.example.jetpackdemeo.test

import android.content.Context
import com.google.common.hash.BloomFilter
import com.google.common.hash.Funnels
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream

object BloomFilterUtil {
    fun generateBloomFilter(list: List<String>): BloomFilter<String> {
        // 此处精度和ios保持一致，ios默认是1.0/(size + 1)
        val bloomFilter :BloomFilter<String> = BloomFilter.create(
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
        bloomFilter?.apply {
            try {
                val outputStream = ByteArrayOutputStream()
                bloomFilter.writeTo(outputStream)

                // 注意，此处需要使用Charsets.ISO_8859_1
                val string = String(outputStream.toByteArray(), Charsets.ISO_8859_1)

//                val outputStream2 = ByteArrayOutputStream()
//                outputStream2.write(string.toByteArray(Charsets.ISO_8859_1))

                context.getSharedPreferences(fileName, Context.MODE_PRIVATE).edit()
                    .putString(valueName, string).apply()

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
        return context.getSharedPreferences(fileName, Context.MODE_PRIVATE).getString(valueName, "")
            ?.run {
                // 注意，此处需要使用Charsets.ISO_8859_1
                BloomFilter.readFrom(
                    ByteArrayInputStream(toByteArray(Charsets.ISO_8859_1)),
                    Funnels.stringFunnel(Charsets.UTF_8)
                )
            }
    }
}