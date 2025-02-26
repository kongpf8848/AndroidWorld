package com.github.kongpf8848.androidworld.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.kongpf8848.androidworld.R
import com.github.kongpf8848.androidworld.adapter.ColorAdapter
import com.github.kongpf8848.androidworld.databinding.FragmentJianshuBinding
import com.github.kongpf8848.androidworld.model.ColorItem
import java.util.*

class Fragment0:BaseFragment<FragmentJianshuBinding>(){


    private val colors = intArrayOf(
        R.color.color_0, R.color.color_1, R.color.color_2, R.color.color_3,
        R.color.color_4, R.color.color_5, R.color.color_6, R.color.color_7,
        R.color.color_8, R.color.color_9
    )

    override fun getLayoutId(): Int {
        return R.layout.fragment_jianshu
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerview.layoutManager=LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val adapter=ColorAdapter(requireContext(),getData())
        binding.recyclerview.adapter=adapter
    }

    private fun getData(): List<ColorItem> {
        val list: MutableList<ColorItem> = ArrayList<ColorItem>()
        for (i in 0..49) {
            list.add(ColorItem(i,colors[i % 10]))
        }
        return list
    }
}