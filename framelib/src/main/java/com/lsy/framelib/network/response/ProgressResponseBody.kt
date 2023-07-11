package com.lsy.framelib.network.response

import okhttp3.MediaType
import okhttp3.ResponseBody
import okio.Buffer
import okio.BufferedSource
import okio.ForwardingSource
import okio.Okio
import timber.log.Timber

/**
 * Title :
 * Author: Lsy
 * Date: 2023/6/14 16:38
 * Version: 1
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
class ProgressResponseBody(
    val responseBody: ResponseBody,
    val onProgressListener: (curSize: Long, totalSize: Long, isDone: Boolean) -> Unit
) : ResponseBody() {
    private val bufferedSource: BufferedSource by lazy {
        Okio.buffer(object : ForwardingSource(responseBody.source()) {
            var totalBytesRead = 0L
            override fun read(sink: Buffer, byteCount: Long): Long =
                super.read(sink, byteCount).also { bytesRead ->
                    // read() returns the number of bytes read, or -1 if this source is exhausted.
                    totalBytesRead += if (bytesRead != -1L) bytesRead else 0
                    onProgressListener(
                        totalBytesRead,
                        responseBody.contentLength(),
                        bytesRead == -1L
                    )
                }
        })
    }

    override fun contentType(): MediaType? = responseBody.contentType()

    override fun contentLength(): Long = responseBody.contentLength()

    override fun source(): BufferedSource = bufferedSource
}