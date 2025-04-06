package com.github.kongpf8848.androidworld.activity

import android.content.Context
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.ListPreloader
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.integration.recyclerview.RecyclerViewPreloader
import com.bumptech.glide.util.ViewPreloadSizeProvider
import com.github.kongpf8848.androidworld.R
import com.github.kongpf8848.androidworld.databinding.ActivityGalleryBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.util.jar.Manifest

class GalleryActivity : BaseActivity<ActivityGalleryBinding>() {
    override fun getLayoutId(): Int {
        return R.layout.activity_gallery
    }

    override fun onCreateEnd(savedInstanceState: Bundle?) {
        super.onCreateEnd(savedInstanceState)
        requestPermissions(arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), 10)

        binding.recyclerView.layoutManager = GridLayoutManager(this, 3).apply {
            orientation = GridLayoutManager.VERTICAL
        }

        val adapter = MyAdapter()
        binding.recyclerView.adapter = adapter

        CoroutineScope(Dispatchers.IO).launch {
            val items = readAllImage(applicationContext)
            withContext(Dispatchers.Main) {
                adapter.onChange(items)
            }
        }

        var preloadSizeProvider = ViewPreloadSizeProvider<MyData>()
        val preloadModelProvider = MyPreloadModelProvider(adapter, this)
        val preloader = RecyclerViewPreloader(
            this,
            preloadModelProvider,
            preloadSizeProvider,
            100 //maxPreload预加载数量
        )

        //binding.recyclerView.addOnScrollListener(preloader)

    }

    class MyPreloadModelProvider(
        private val adapter: MyAdapter,
        private val context: Context
    ) : ListPreloader.PreloadModelProvider<MyData> {
        // 从当前屏幕结束的位置开始，再加载maxPreload条item，
        // 不可见的item即为屏幕外的position位置
        // 如果对于一个给定的位置不需要加载任何东西，getPreloadItems 可以返回一个空表。
        override fun getPreloadItems(position: Int): MutableList<MyData> {
            val data: MyData = adapter.getItems()[position]
            Log.d(TAG, "getPreloadItems pos:$position index:${data.index}")
            return mutableListOf(data)
        }

        override fun getPreloadRequestBuilder(item: MyData): RequestBuilder<*>? {
            Log.d(TAG, "getPreloadRequestBuilder ${item.index}")
            return Glide.with(context).load(File(item.path))
        }
    }

    class MyAdapter : RecyclerView.Adapter<MyViewHolder>() {
        private var items: ArrayList<MyData>? = null

        fun onChange(items: ArrayList<MyData>?) {
            this.items = items
            notifyDataSetChanged()
        }

        fun getItems(): ArrayList<MyData> {
            return items!!
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_gallery, parent, false)
            return MyViewHolder(view)
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            Glide.with(holder.itemView.context).load(File(items?.get(position)?.path))
                .into(holder.image)
        }

        override fun getItemCount(): Int {
            return items?.size ?: 0
        }
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.image)
    }

    private fun readAllImage(context: Context): ArrayList<MyData> {
        val photos = ArrayList<MyData>()

        //读取手机图片
        val cursor = context.contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            null,
            null,
            null,
            null
        )
        var index = 0
        while (cursor!!.moveToNext()) {
            //图片路径
            val path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA))

            //图片名称
            val name =
                cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME))

            //图片大小
            val size = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.SIZE))
            Log.d(TAG, "readAllImage: $path,$name,$size")

            photos.add(MyData(path, index++))
        }
        cursor.close()

        return photos
    }

    class MyData(var path: String, val index: Int)

    companion object {
        private const val TAG = "GalleryActivity"
    }
}
