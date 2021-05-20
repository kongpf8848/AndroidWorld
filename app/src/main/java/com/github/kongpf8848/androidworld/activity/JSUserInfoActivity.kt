package com.github.kongpf8848.androidworld.activity

import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.DynamicDrawableSpan
import android.text.style.ImageSpan
import android.view.View
import androidx.core.graphics.ColorUtils
import androidx.fragment.app.Fragment
import com.github.kongpf8848.androidworld.R
import com.github.kongpf8848.androidworld.adapter.FragmentAdapter
import com.github.kongpf8848.androidworld.fragment.Fragment0
import com.github.kongpf8848.androidworld.fragment.Fragment1
import com.github.kongpf8848.androidworld.fragment.Fragment2
import com.github.kongpf8848.androidworld.fragment.Fragment3
import com.google.android.material.appbar.AppBarLayout
import com.gyf.immersionbar.ImmersionBar
import kotlinx.android.synthetic.main.activity_js_user_info.*
import kotlinx.android.synthetic.main.include_user_info.*
import java.util.*

class JSUserInfoActivity:BaseActivity(){

    private val titlesList= listOf("动态","文章","帖子","更多")

    override fun getLayoutId(): Int {
        return R.layout.activity_js_user_info
    }

    override fun getStatusBarView(): View? {
        return view_status_height
    }

    override fun statusBarDarkFont(): Boolean {
        return false
    }

    override fun onCreateEnd(savedInstanceState: Bundle?) {
        super.onCreateEnd(savedInstanceState)
        iv_toolbar_back.setOnClickListener {
            finish()
        }

        val fragments = ArrayList<Fragment>()
        fragments.add(Fragment0())
        fragments.add(Fragment1())
        fragments.add(Fragment2())
        fragments.add(Fragment3())

        val adapter = FragmentAdapter(supportFragmentManager, fragments, titlesList)
        viewpager_container.adapter = adapter
        tablayout_user.setupWithViewPager(viewpager_container)

        iv_cover.setImageResource(R.mipmap.ic_scene)
        val text="    |  8829 字  |  89 赞"
        val ssb = SpannableStringBuilder()
        ssb.append(text)
        val imageSpan= ImageSpan(applicationContext,R.mipmap.icon_gender_male, DynamicDrawableSpan.ALIGN_CENTER)
        ssb.setSpan(
            imageSpan,
            0,
            1,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        tv_user_info.text=ssb

        app_bar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            val ratio = Math.abs(verticalOffset) * 1.0f / appBarLayout.totalScrollRange
            view_status_height.setBackgroundColor(ColorUtils.blendARGB(Color.TRANSPARENT, Color.WHITE, ratio))
            view_toolbar_bg.setBackgroundColor(ColorUtils.blendARGB(Color.TRANSPARENT, Color.WHITE, ratio))
            iv_toolbar_back.isSelected = ratio >= 0.5
            iv_toolbar_nav.isSelected=ratio >= 0.5
            iv_toolbar_search.isSelected = ratio >= 0.5
            toolbar_title.visibility = if (ratio >= 0.5) View.VISIBLE else View.INVISIBLE
            setStatusBarDarkFont(ratio >= 0.5)
        })

    }
}