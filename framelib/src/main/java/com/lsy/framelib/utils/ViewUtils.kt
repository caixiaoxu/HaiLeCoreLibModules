package com.lsy.framelib.utils

import android.view.MotionEvent
import android.widget.TextView

/**
 * Title : 控件工具类
 * Author: Lsy
 * Date: 2023/4/3 17:19
 * Version: 1
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
object ViewUtils {
    private const val MIN_CLICK_DELAY_TIME = 500 // 两次点击事件的最小时间间隔
    private var lastClickTime: Long = 0 // 上一次点击事件的时间戳

    /**
     * 防重复点击
     */
    fun isFastDoubleClick(): Boolean {
        val currentTime = System.currentTimeMillis()
        val timeInterval = currentTime - lastClickTime
        if (timeInterval < MIN_CLICK_DELAY_TIME) {
            return true
        }
        lastClickTime = currentTime
        return false
    }

    /**
     * 处理edittext和scrollview滑动问题
     */
    fun editTextSlide(result: TextView) {
        result.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN || event.action == MotionEvent.ACTION_MOVE) {
                //这句话说的意思告诉父View我自己的事件我自己处理
                v.parent.requestDisallowInterceptTouchEvent(true)
            }
            false
        }
    }
}