package com.lsy.framelib.network.exception

/**
 * Title : Token过期异常
 * Author: Lsy
 * Date: 2023/3/17 16:22
 * Version: 1
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
class CommonCustomException(val code: Int, message: String?) : Exception(message) {

}