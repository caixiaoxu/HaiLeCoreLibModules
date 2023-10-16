package com.lsy.framelib.utils

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.core.content.FileProvider
import java.io.File


/**
 * Title :
 * Author: Lsy
 * Date: 2023/6/13 20:50
 * Version: 1
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
object AppPackageUtils {

    /**
     *  获取当前程序的版本号
     */
    fun getVersionName(context: Context): String = try {
        context.packageManager.getPackageInfo(context.packageName, 0).versionName
    } catch (e: PackageManager.NameNotFoundException) {
        e.printStackTrace()
        ""
    }

    fun installApk(context: Context, apkFile: File) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
            val uri = FileProvider.getUriForFile(
                context,
                "${context.packageName}.fileprovider",
                apkFile
            )
            intent.setDataAndType(uri, "application/vnd.android.package-archive")
        } else {
            intent.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive")
        }
        context.startActivity(intent)
    }


    /**
     * 判断是否安装某个应用
     */
    fun checkAppInstalled(context: Context, pkgName: String?): Boolean {
        if (pkgName == null || pkgName.isEmpty()) {
            return false
        }
        val packageManager: PackageManager = context.packageManager
        val info = packageManager.getInstalledPackages(0)
        if (info.isNullOrEmpty()) {
            return false
        }
        for (i in info.indices) {
            if (pkgName == info[i].packageName) {
                return true
            }
        }
        return false
    }

    /**
     * 通过包名 在应用商店打开应用
     *
     * @param packageName 包名
     */
    fun openApplicationMarket(context: Context, packageName: String) {
        // 新版应用商店不再支付跳转
//        try {
//            val str = "market://details?id=$packageName"
//            val localIntent = Intent(Intent.ACTION_VIEW)
//            localIntent.data = Uri.parse(str)
//            localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//            context.startActivity(localIntent)
//        } catch (e: Exception) {
//            // 打开应用商店失败 可能是手机没有安装应用市场
//            e.printStackTrace()
//            // 调用系统浏览器进入商城
            val url = "http://app.mi.com/detail/163525?ref=search"
            openLinkBySystem(context, url)
//        }
    }

    /**
     * 调用系统浏览器打开网页
     *
     * @param url 地址
     */
    fun openLinkBySystem(context: Context, url: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        context.startActivity(intent)
    }
}