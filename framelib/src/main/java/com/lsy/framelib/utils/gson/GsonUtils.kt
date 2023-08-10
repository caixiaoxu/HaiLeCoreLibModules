package com.lsy.framelib.utils.gson

import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import org.json.JSONObject
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import java.util.*

/**
 * Title :
 * Author: Lsy
 * Date: 2023/4/6 10:41
 * Version: 1
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
object GsonUtils {

    val gson by lazy {
        GsonBuilder()
            .registerTypeAdapter(String::class.java, NullStringAdapter())
            .registerTypeAdapter(Int::class.java, IntDeserializer())
            .registerTypeAdapter(Long::class.java, LongDeserializer())
            .registerTypeAdapter(Double::class.java, DoubleDeserializer())
            .registerTypeAdapter(Date::class.java, DateSerializer())
            .registerTypeAdapter(Date::class.java, DateDeserializer())
            .create()
    }

    fun <T> getValueOfKey(json: String, key: String): T? =
        try {
            JSONObject(json).get(key) as? T
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }

    /**
     * 转json
     */
    fun any2Json(any: Any?): String = gson.toJson(any)

    /**
     * json转对象
     */
    fun <T> json2Class(json: String?, clz: Class<T>): T? =
        json?.let { gson.fromJson(it, clz) }

    /**
     * json转对象(泛型)
     */
    fun <T> json2ClassType(json: String?, type: Type?): T? =
        json?.let { gson.fromJson(it, type) }

    /**
     * json转json对象
     */
    fun json2JsonObject(json: String?): JsonObject? = try {
        JsonParser.parseString(json).asJsonObject
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }

    /**
     * json转指定类型
     */
    fun <T> json2List(json: String?, clz: Class<T>): MutableList<T>? =
        gson.fromJson(json, ParameterizedTypeImpl(clz))

    internal class ParameterizedTypeImpl<T>(private val clz: Class<T>) : ParameterizedType {
        override fun getActualTypeArguments(): Array<Type> = arrayOf(clz)
        override fun getRawType(): Type = MutableList::class.java

        override fun getOwnerType(): Type? = null
    }
}