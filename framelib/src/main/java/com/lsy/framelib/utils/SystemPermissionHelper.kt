package com.lsy.framelib.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat

/**
 * Title :
 * Author: Lsy
 * Date: 2023/7/17 11:16
 * Version: 1
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
object SystemPermissionHelper {

    /**
     * android 12之前的权限
     */
    object NormalPermissions {
        val readWritePermissions = arrayOf(
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        val cameraPermissions = arrayOf(
            Manifest.permission.CAMERA
        )
        val InstallPackagesPermissions = arrayOf(
            Manifest.permission.REQUEST_INSTALL_PACKAGES
        )

        val LocationPermissions = arrayOf(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
        )

        val PhoneStatePermissions = arrayOf(
            Manifest.permission.READ_PHONE_STATE,
        )

        val CallPhonePermissions = arrayOf(
            Manifest.permission.CALL_PHONE,
        )
    }

    /**
     * android 13以后的权限
     */
    object Android13Permissions {
        @RequiresApi(Build.VERSION_CODES.TIRAMISU)
        val readWritePermissions = arrayOf(
            Manifest.permission.READ_MEDIA_IMAGES,
            Manifest.permission.READ_MEDIA_VIDEO,
            Manifest.permission.READ_MEDIA_AUDIO,
        )
    }

    /**
     * 读写权限
     */
    fun readWritePermissions() =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) Android13Permissions.readWritePermissions
        else NormalPermissions.readWritePermissions

    /**
     * 定位权限
     */
    fun locationPermissions() = NormalPermissions.LocationPermissions

    /**
     * 相机权限
     */
    fun cameraPermissions() = NormalPermissions.cameraPermissions

    /**
     * 手机状态权限
     */
    fun phoneStatePermissions() = NormalPermissions.PhoneStatePermissions

    /**
     * 拨号权限
     */
    fun callPhonePermissions() = NormalPermissions.CallPhonePermissions

    /**
     * 安装权限
     */
    fun installPackagesPermissions() = NormalPermissions.InstallPackagesPermissions

    /**
     * 判断是否有权限
     */
    fun checkPermissions(context: Context, permissions: Array<String>): Boolean =
        permissions.all { checkPermission(context, it) }

    /**
     * 判断是否有权限
     */
    fun checkPermission(context: Context, permission: String): Boolean =
        ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED
}