package com.lsy.framelib.ui.weight.loading

import androidx.fragment.app.FragmentManager
import com.lsy.framelib.R

/**
 * Title : 加载Dialog管理
 * Author: Lsy
 * Date: 2023/3/20 18:04
 * Version: 1
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
object LoadDialogMgr {
    private const val LOAD_TIP_DIALOG_TAG = "LoadTipDialog"

    var loadingRes = R.drawable.loading_animation

    /**
     * 显示Loading弹窗
     */
    fun showLoadingDialog(fragmentManager: FragmentManager) {
        val fragment = fragmentManager.findFragmentByTag(LOAD_TIP_DIALOG_TAG)
        val loadingDialog = if (null != fragment && fragment is LoadingDialog) {
            fragment
        } else {
            LoadingDialog(loadingRes)
        }
        if (loadingDialog.isAdded) {
            return
        }
        fragmentManager.beginTransaction().remove(loadingDialog)
            .add(loadingDialog, LOAD_TIP_DIALOG_TAG)
            .commitNowAllowingStateLoss()
    }

    /**
     * 隐藏Loading弹窗
     */
    fun hideLoadingDialog(fragmentManager: FragmentManager) {
        val fragment = fragmentManager.findFragmentByTag(LOAD_TIP_DIALOG_TAG)
        if (null != fragment && fragment is LoadingDialog) {
            fragment.dismissAllowingStateLoss()
        }
    }

//    fun showDialog() {
//        val activity = ActivityUtil.getTopActivity()
//        if (activity is FragmentActivity) {
//            showDialog(activity)
//        }
//    }
//
//    fun closeDialog() {
//        val activity = ActivityUtil.getTopActivity()
//        if (activity is FragmentActivity) {
//            closeDialog(activity)
//        }
//    }
}