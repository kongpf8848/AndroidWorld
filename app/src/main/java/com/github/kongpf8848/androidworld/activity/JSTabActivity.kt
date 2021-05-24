package com.github.kongpf8848.androidworld.activity

import android.animation.*
import android.os.Bundle
import android.view.View
import com.github.kongpf8848.androidworld.R
import kotlinx.android.synthetic.main.activity_image_scale_type.*
import kotlinx.android.synthetic.main.activity_js_tab.*
import kotlinx.android.synthetic.main.activity_js_tab.toolbar

class JSTabActivity:BaseActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_js_tab
    }

    override fun onCreateEnd(savedInstanceState: Bundle?) {
        super.onCreateEnd(savedInstanceState)
        toolbar.setNavigationOnClickListener {
            finish()
        }

        button1.setOnClickListener {
            doAnimation1()
        }
        button2.setOnClickListener {
            doAnimation2()
        }
    }

    private fun doAnimation1() {

        val animator1 = ObjectAnimator.ofPropertyValuesHolder(iv_home_page,
            PropertyValuesHolder.ofFloat(View.ALPHA, 1f, 0f),
            PropertyValuesHolder.ofFloat(View.SCALE_X, 1f, 0f),
            PropertyValuesHolder.ofFloat(View.SCALE_Y, 1f, 0f)
        ).apply {
            addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    super.onAnimationEnd(animation)
                    iv_home_page.visibility = View.GONE
                }
            })
        }

        val animator2 = ObjectAnimator.ofPropertyValuesHolder(
            iv_refresh,
            PropertyValuesHolder.ofFloat(View.ALPHA, 0f, 1f),
            PropertyValuesHolder.ofFloat(View.ROTATION, 0f, 360f)
        ).apply {
            addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationStart(animation: Animator?) {
                    super.onAnimationStart(animation)
                    iv_refresh.visibility = View.VISIBLE
                }
            })
        }

        AnimatorSet().apply {
            duration = 500
            playTogether(animator1,animator2)
        }.start()

    }

    private fun doAnimation2() {

        val animator1 = ObjectAnimator.ofPropertyValuesHolder(iv_home_page,
                PropertyValuesHolder.ofFloat(View.ALPHA, 0f, 1f),
                PropertyValuesHolder.ofFloat(View.SCALE_X, 0f, 1f),
                PropertyValuesHolder.ofFloat(View.SCALE_Y, 0f, 1f)
        ).apply {
            addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationStart(animation: Animator?) {
                    super.onAnimationStart(animation)
                    iv_home_page.visibility = View.VISIBLE
                }
            })
        }

        val animator2 = ObjectAnimator.ofPropertyValuesHolder(
                iv_refresh,
                PropertyValuesHolder.ofFloat(View.ALPHA, 1f, 0f),
                PropertyValuesHolder.ofFloat(View.ROTATION, 360f, 0f)
        ).apply {
            addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    super.onAnimationEnd(animation)
                    iv_refresh.visibility = View.GONE
                }
            })
        }

        AnimatorSet().apply {
            duration = 500
            playTogether(animator1,animator2)
        }.start()


    }
}