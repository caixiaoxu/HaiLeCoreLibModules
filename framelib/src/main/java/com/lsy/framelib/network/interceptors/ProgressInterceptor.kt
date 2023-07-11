package com.lsy.framelib.network.interceptors

import com.lsy.framelib.network.response.ProgressResponseBody
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Title :
 * Author: Lsy
 * Date: 2023/6/14 16:52
 * Version: 1
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
class ProgressInterceptor(private val onProgressListener: (curSize: Long, totalSize: Long, isDone: Boolean) -> Unit) :
    Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(chain.request()).let { originalResponse ->
            originalResponse.newBuilder()
                .body(ProgressResponseBody(originalResponse.body()!!, onProgressListener)).build()
        }
    }
}