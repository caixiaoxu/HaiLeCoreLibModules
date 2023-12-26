package com.lsy.framelib.utils

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.graphics.Point
import android.view.Display
import android.view.View
import android.view.View.OnAttachStateChangeListener
import android.view.WindowManager
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.lang.Integer.min
import kotlin.math.abs
import kotlin.math.max


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

    var mHasCheckAllScreen: Boolean = false
    var mIsAllScreen: Boolean = false

    fun isFullScreenDevice(context: Context): Boolean {
        if (mHasCheckAllScreen) return mIsAllScreen
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val defaultDisplay: Display = wm.defaultDisplay
        //屏幕实际高度
        val realPoint = Point()
        defaultDisplay.getRealSize(realPoint)

        val screenWidth = min(realPoint.x, realPoint.y)
        val screenHeight = max(realPoint.x, realPoint.y)
        if (screenHeight / screenWidth >= 1.97f) {
            mIsAllScreen = true
        }
        mHasCheckAllScreen = true
        return mIsAllScreen
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

    fun hasNavigationBars(activity: Activity, callback: (barHeight: Int) -> Unit) {
        val decorView = activity.window.decorView
        if (decorView.isAttachedToWindow) {
            getNavigationBarsHeight(decorView,callback)
        } else {
            decorView.addOnAttachStateChangeListener(object : OnAttachStateChangeListener {
                override fun onViewAttachedToWindow(v: View) {
                    getNavigationBarsHeight(v,callback)
                }

                override fun onViewDetachedFromWindow(v: View) {
                }
            })
        }
    }

    private fun getNavigationBarsHeight(decorView: View, callback: (barHeight: Int) -> Unit) {
        ViewCompat.getRootWindowInsets(decorView)?.let { windowInsets ->
            val navigationInsets =
                windowInsets.getInsets(WindowInsetsCompat.Type.navigationBars())
            val hasNavigationBar =
                windowInsets.isVisible(WindowInsetsCompat.Type.navigationBars()) && navigationInsets.bottom > 0
            callback(
                if (hasNavigationBar) {
                    val height = abs(navigationInsets.bottom - navigationInsets.top)
                    if (0 == height) getNavigationBarHeight(decorView.context) else height
                } else 0
            )
        }
    }

    fun getNavigationBarHeight(context: Context): Int {
        val resourceId: Int =
            context.resources.getIdentifier("navigation_bar_height", "dimen", "android")
        return if (resourceId > 0) context.resources.getDimensionPixelSize(resourceId) else 0
    }
}