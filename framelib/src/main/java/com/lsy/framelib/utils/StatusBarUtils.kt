package com.lsy.framelib.utils

import android.content.Context
import android.content.res.Resources
import android.graphics.Point
import android.graphics.Rect
import android.view.Display
import android.view.View
import android.view.WindowManager
import androidx.core.view.ViewCompat


/**
 * Title : 状态栏工具类
 * Author: Lsy
 * Date: 2023/4/3 10:41
 * Version: 1
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
object StatusBarUtils {

    fun isFullScreenDevice(context: Context): Boolean {
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val defaultDisplay: Display = wm.defaultDisplay
        //屏幕实际高度
        val realPoint = Point()
        defaultDisplay.getRealSize(realPoint)
        val screenWidth = realPoint.x
        val screenHeight = realPoint.y

        //屏幕显示高度
        val rect = Rect()
        defaultDisplay.getRectSize(rect)
        val usableScreenWidth = rect.width()
        val usableScreenHeight = rect.height()

        return screenWidth != usableScreenWidth || screenHeight != usableScreenHeight
    }

    /**
     * Return the status bar's height.
     * 返回状态栏的高度
     *
     * @return the status bar's height
     */
    fun getStatusBarHeight(): Int {
        val resources: Resources = Resources.getSystem()
        val resourceId: Int = resources.getIdentifier("status_bar_height", "dimen", "android")
        return if (resourceId > 0) resources.getDimensionPixelSize(resourceId) else 0
    }


    /**
     * 状态状态栏文字颜色
     */
    fun setStatusBarDarkTheme(view: View, dark: Boolean) {
        val controller = ViewCompat.getWindowInsetsController(view)
        controller?.isAppearanceLightStatusBars = dark
    }

    fun getNavigationBarHeight(): Int {
        val resources: Resources = Resources.getSystem()
        val resourceId: Int = resources.getIdentifier("navigation_bar_height", "dimen", "android")
        return if (resourceId > 0) resources.getDimensionPixelSize(resourceId) else 0
    }
}