package com.github.kongpf8848.androidworld.activity

import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import androidx.core.view.updateLayoutParams
import com.github.kongpf8848.androidworld.R
import com.github.kongpf8848.androidworld.utils.KeyboardUtils
import kotlinx.android.synthetic.main.activity_keyboard.*

class KeyboardActivity : BaseActivity() {
    private var keyboardUtils: KeyboardUtils? = null
    override fun getLayoutId(): Int {
        return R.layout.activity_keyboard
    }

    override fun onCreateEnd(savedInstanceState: Bundle?) {
        super.onCreateEnd(savedInstanceState)
        keyboardUtils = KeyboardUtils(
            this
        ) { isPopup, keyboardHeight ->
            Log.d(
                TAG,
                "onKeyboardChange() called with: isPopup = [$isPopup], keyboardHeight = [$keyboardHeight]"
            )
            tv_refresh.updateLayoutParams<FrameLayout.LayoutParams> {
                bottomMargin = keyboardHeight
            }
        }.apply {
            enable()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        keyboardUtils?.disable()
    }
}