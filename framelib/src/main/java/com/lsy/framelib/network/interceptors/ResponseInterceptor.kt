package com.lsy.framelib.network.interceptors

import com.lsy.framelib.network.intfs.IInterceptor
import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber

/**
 * Title : 处理response的异常code
 * Author: Lsy
 * Date: 2023/3/17 16:20
 * Version: 1
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 * @param handlerResponseException 自定义异常code处理
 */
class ResponseInterceptor(private val handlerResponseException: ((response: Response) -> Unit)? = null) :
    IInterceptor {
    override fun getInterceptor(): Interceptor {
        return Interceptor { chain ->
            val response = chain.proceed(chain.request())
            // 自定义处理
            handlerResponseException?.invoke(response)
            // 默认处理
            when (response.code()) {
                200 -> response
                else -> {
                    Timber.e("错误码：${response.code()}, 错误信息${response.message()}")
                    response
                }
            }
        }
    }
}