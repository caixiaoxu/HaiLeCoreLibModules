package com.lsy.framelib.ui.base.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.lsy.framelib.utils.SoftHideKeyBoardUtil

/**
 * Title : Activity基类(构建布局DataBinding)
 * Author: Lsy
 * Date: 2023/3/16 14:25
 * Version: 1
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
abstract class BaseBindingActivity<T : ViewDataBinding> : BaseActivity() {
    protected val mBinding: T by lazy {
        DataBindingUtil.setContentView(this, layoutId())
    }

    private var softHideKeyBoardUtil: SoftHideKeyBoardUtil? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding.lifecycleOwner = this
        if (needSoftHideKeyBoard()) {
            softHideKeyBoardUtil = SoftHideKeyBoardUtil(this)
        }
        initStatusBarTxtColor(mBinding.root)
    }

    open fun needSoftHideKeyBoard(): Boolean = true

    /**
     * 根布局
     */
    abstract fun layoutId(): Int

    override fun onDestroy() {
        softHideKeyBoardUtil?.onDestroy()
        super.onDestroy()
    }
}