package com.github.kongpf8848.androidworld.activity

import android.animation.*
import android.os.Bundle
import android.view.View
import com.github.kongpf8848.androidworld.R
import com.github.kongpf8848.androidworld.databinding.ActivityJsTabBinding


class JSTabActivity : BaseActivity<ActivityJsTabBinding>() {
    override fun getLayoutId(): Int {
        return R.layout.activity_js_tab
    }

    override fun onCreateEnd(savedInstanceState: Bundle?) {
        super.onCreateEnd(savedInstanceState)
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

        binding.button1.setOnClickListener {
            doAnimation1()
        }
        binding.button2.setOnClickListener {
            doAnimation2()
        }
    }

    private fun doAnimation1() {

        val animator1 = ObjectAnimator.ofPropertyValuesHolder(
            binding.ivHomePage,
            PropertyValuesHolder.ofFloat(View.ALPHA, 1f, 0f),
            PropertyValuesHolder.ofFloat(View.SCALE_X, 1f, 0f),
            PropertyValuesHolder.ofFloat(View.SCALE_Y, 1f, 0f)
        ).apply {
            addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    super.onAnimationEnd(animation)
                    binding.ivHomePage.visibility = View.GONE
                }
            })
        }

        val animator2 = ObjectAnimator.ofPropertyValuesHolder(
            binding.ivRefresh,
            PropertyValuesHolder.ofFloat(View.ALPHA, 0f, 1f),
            PropertyValuesHolder.ofFloat(View.ROTATION, 0f, 360f)
        ).apply {
            addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationStart(animation: Animator) {
                    super.onAnimationStart(animation)
                    binding.ivRefresh.visibility = View.VISIBLE
                }
            })
        }

        AnimatorSet().apply {
            duration = 500
            playTogether(animator1, animator2)
        }.start()

    }

    private fun doAnimation2() {

        val animator1 = ObjectAnimator.ofPropertyValuesHolder(
            binding.ivHomePage,
            PropertyValuesHolder.ofFloat(View.ALPHA, 0f, 1f),
            PropertyValuesHolder.ofFloat(View.SCALE_X, 0f, 1f),
            PropertyValuesHolder.ofFloat(View.SCALE_Y, 0f, 1f)
        ).apply {
            addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationStart(animation: Animator) {
                    super.onAnimationStart(animation)
                    binding.ivHomePage.visibility = View.VISIBLE
                }
            })
        }

        val animator2 = ObjectAnimator.ofPropertyValuesHolder(
            binding.ivRefresh,
            PropertyValuesHolder.ofFloat(View.ALPHA, 1f, 0f),
            PropertyValuesHolder.ofFloat(View.ROTATION, 360f, 0f)
        ).apply {
            addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    super.onAnimationEnd(animation)
                    binding.ivRefresh.visibility = View.GONE
                }
            })
        }

        AnimatorSet().apply {
            duration = 500
            playTogether(animator1, animator2)
        }.start()


    }
}