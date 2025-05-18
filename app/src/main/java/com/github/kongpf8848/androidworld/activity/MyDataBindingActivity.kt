package com.github.kongpf8848.androidworld.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.ObservableField
import com.github.kongpf8848.androidworld.R
import com.github.kongpf8848.androidworld.databinding.ActivityMyDatabindingBinding
import com.github.kongpf8848.androidworld.model.User
import com.github.kongpf8848.androidworld.viewmodel.MyViewModel


/**
 * 对于输入控件，使用@={}表达式即可实现页面和绑定的值双向自动刷新
 */
class MyDataBindingActivity : BaseActivity<ActivityMyDatabindingBinding>() {

    var inputMsg: ObservableField<String?>? = null
    private val viewModel: MyViewModel by viewModels()

    interface MyClick {
        fun onClick(v: View)
    }

    interface MyClick2 {
        fun onClick(user: User)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_my_databinding
    }

    override fun onCreateEnd(savedInstanceState: Bundle?) {
        super.onCreateEnd(savedInstanceState)
        binding.user = User(name = "jack", age = 20)
        binding.clickListener = object : MyClick {
            override fun onClick(v: View) {
                Log.d(TAG, "onClick: View")
                binding.user?.updateName("sky")
            }
        }

        binding.clickListener2 = object : MyClick2 {
            override fun onClick(user: User) {
                Log.d(TAG, "onClick: User:${user}")
            }
        }
        binding.button3.setOnClickListener {
            binding.vm?.userName?.value="cat"
        }

        inputMsg = ObservableField("msg")
        binding.inputMsg = inputMsg

        binding.vm = viewModel
    }
}