package com.github.kongpf8848.androidworld.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.kongpf8848.androidworld.R
import com.github.kongpf8848.androidworld.utils.LanguageUtils
import kotlinx.android.synthetic.main.activiyt_lauguage.*

class LanguageActivity : BaseActivity() {
    private var tmpLanguage:String?=""
    private var isFollowSystem:Boolean=true

    override fun getLayoutId(): Int {
        return R.layout.activiyt_lauguage
    }

    override fun onCreateEnd(savedInstanceState: Bundle?) {
        super.onCreateEnd(savedInstanceState)
        toolbar.setNavigationOnClickListener {
            finish()
        }
        isFollowSystem=LanguageUtils.isFollowSystem(this@LanguageActivity)
        tmpLanguage=LanguageUtils.getCurrentLanguage(this@LanguageActivity)

        recyclerview.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerview.adapter = object : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
            override fun onCreateViewHolder(
                parent: ViewGroup,
                viewType: Int
            ): RecyclerView.ViewHolder = object : RecyclerView.ViewHolder(
                LayoutInflater.from(this@LanguageActivity).inflate(R.layout.item_language, parent, false)
            ){}

            override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
                val tv_language:TextView=holder.itemView.findViewById(R.id.tv_language)
                val iv_checked:ImageView=holder.itemView.findViewById(R.id.iv_checked)
                if(position==0){
                    tv_language.text =getString(R.string.follow_system)
                    iv_checked.visibility=if(isFollowSystem)View.VISIBLE else View.GONE
                }
                else{
                    tv_language.text =LanguageUtils.LANGUAGE_LIST[position-1].second
                    val itemLanguage = LanguageUtils.LANGUAGE_LIST[position-1].first
                    if(isFollowSystem){
                        iv_checked.visibility=View.GONE
                    }
                    else {
                        iv_checked.visibility = if (itemLanguage == tmpLanguage) View.VISIBLE else View.GONE
                    }
                }

                holder.itemView.setOnClickListener {
                    if(position==0){
                        isFollowSystem=true
                        tmpLanguage=""
                    }
                    else{
                        isFollowSystem=false
                        tmpLanguage=LanguageUtils.LANGUAGE_LIST[position-1].first
                    }
                    notifyDataSetChanged()
                }
            }

            override fun getItemCount(): Int {
                return LanguageUtils.LANGUAGE_LIST.size+1
            }

        }

        btn_save.setOnClickListener {
            LanguageUtils.switchLanguage(this@LanguageActivity,isFollowSystem,tmpLanguage)
        }

    }
}