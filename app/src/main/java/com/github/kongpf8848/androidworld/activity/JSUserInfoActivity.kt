package com.github.kongpf8848.androidworld.activity


import android.graphics.Color
import android.os.Bundle
import android.os.Looper
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.DynamicDrawableSpan
import android.text.style.ImageSpan
import android.util.Log
import android.view.View
import androidx.core.graphics.ColorUtils
import androidx.fragment.app.Fragment
import com.github.kongpf8848.androidworld.R
import com.github.kongpf8848.androidworld.adapter.FragmentAdapter
import com.github.kongpf8848.androidworld.databinding.ActivityJsUserInfoBinding
import com.github.kongpf8848.androidworld.fragment.Fragment0
import com.github.kongpf8848.androidworld.fragment.Fragment1
import com.github.kongpf8848.androidworld.fragment.Fragment2
import com.github.kongpf8848.androidworld.fragment.Fragment3
import com.google.android.material.tabs.TabLayout


class JSUserInfoActivity : BaseActivity<ActivityJsUserInfoBinding>() {

    private val titlesList = listOf("我的动态", "文章", "帖子", "更多")

    override fun getLayoutId(): Int {
        return R.layout.activity_js_user_info
    }

    override fun getStatusBarView(): View? {
        return binding.viewStatusHeight
    }

    override fun statusBarDarkFont(): Boolean {
        return false
    }

    override fun onCreateEnd(savedInstanceState: Bundle?) {
        super.onCreateEnd(savedInstanceState)
        binding.ivToolbarBack.setOnClickListener {
            finish()
        }

        val fragments = ArrayList<Fragment>()
        fragments.add(Fragment0())
        fragments.add(Fragment1())
        fragments.add(Fragment2())
        fragments.add(Fragment3())

        val adapter = FragmentAdapter(supportFragmentManager, fragments, titlesList)
        binding.viewpagerContainer.adapter = adapter
        binding.tablayoutUser.setupWithViewPager(binding.viewpagerContainer)


        binding.tablayoutUser.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                Log.d(TAG, "onTabSelected() called with: tab = $tab")
                //binding.appBar.setExpanded(false)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                Log.d(TAG, "onTabUnselected() called with: tab = $tab")
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                Log.d(TAG, "onTabReselected() called with: tab = $tab")
            }

        })


        binding.ivCover.setImageResource(R.mipmap.ic_scene)
        val text = "    |  8829 字  |  89 赞"
        val ssb = SpannableStringBuilder()
        ssb.append(text)
        val imageSpan = ImageSpan(
            applicationContext,
            R.mipmap.icon_gender_male,
            DynamicDrawableSpan.ALIGN_CENTER
        )
        ssb.setSpan(
            imageSpan,
            0,
            1,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        binding.tvUserInfo.text = ssb

        binding.appBar.addOnOffsetChangedListener { appBarLayout, verticalOffset ->
            val ratio = Math.abs(verticalOffset) * 1.0f / appBarLayout.totalScrollRange
            binding.viewStatusHeight.setBackgroundColor(
                ColorUtils.blendARGB(
                    Color.TRANSPARENT,
                    Color.WHITE,
                    ratio
                )
            )
            binding.viewToolbarBg.setBackgroundColor(
                ColorUtils.blendARGB(
                    Color.TRANSPARENT,
                    Color.WHITE,
                    ratio
                )
            )
            binding.ivToolbarBack.isSelected = ratio >= 0.5
            binding.ivToolbarNav.isSelected = ratio >= 0.5
            binding.ivToolbarSearch.isSelected = ratio >= 0.5
            binding.toolbarTitle.visibility = if (ratio >= 0.5) View.VISIBLE else View.INVISIBLE
            setStatusBarDarkFont(ratio >= 0.5)
        }

        Looper.myQueue().addIdleHandler {
            Log.d(TAG, "addIdleHandler called")
            val tab = binding.tablayoutUser.getTabAt(1)
            if(tab!=null){

                // 创建或获取 Badge
                val badgeDrawable = tab.getOrCreateBadge()
                // 配置红点
                badgeDrawable.isVisible = true
                badgeDrawable.number = 8 // 设置未读数（0 时显示小红点）

                // 可选自定义样式
                badgeDrawable.backgroundColor = Color.RED
                badgeDrawable.badgeTextColor = Color.WHITE
                badgeDrawable.verticalOffset = 15 // 垂直偏移调整位置
                badgeDrawable.horizontalOffset = -15 // 水平偏移调整位置
            }

            false
        }

    }
}