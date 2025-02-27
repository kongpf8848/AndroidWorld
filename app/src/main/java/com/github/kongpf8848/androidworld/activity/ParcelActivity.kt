package com.github.kongpf8848.androidworld.activity

import android.os.Bundle
import com.github.kongpf8848.androidworld.R
import com.github.kongpf8848.androidworld.databinding.ActivityParcelBinding

class ParcelActivity : BaseActivity<ActivityParcelBinding>() {
    override fun getLayoutId(): Int {
        return R.layout.activity_parcel
    }

    override fun onCreateEnd(savedInstanceState: Bundle?) {
        super.onCreateEnd(savedInstanceState)
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

    }
}