package com.github.kongpf8848.androidworld.activity

import android.os.Bundle
import android.util.Log
import com.github.kongpf8848.androidworld.R
import com.github.kongpf8848.androidworld.databinding.ActivityOkhttpBinding
import com.github.kongpf8848.androidworld.utils.HttpEventListener
import okhttp3.*
import java.io.IOException
import java.lang.String
import java.net.Proxy

class OkHttpActivity : BaseActivity<ActivityOkhttpBinding>() {

    override fun getLayoutId(): Int {
        return R.layout.activity_okhttp
    }

    override fun onCreateEnd(savedInstanceState: Bundle?) {
        super.onCreateEnd(savedInstanceState)

        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
        binding.button1.setOnClickListener {
            val client = OkHttpClient.Builder()
                .addInterceptor(LoggingInterceptor())
                .eventListener(HttpEventListener())
                .proxy(Proxy.NO_PROXY)
                .build()



            val request = Request.Builder()
                .url("https://publicobject.com/helloworld.txt")
                //https://publicobject.com/helloworld.txt
                .header("Name", "OkHttp Example")
                .build()

            client.newCall(request).enqueue(object: Callback{
                override fun onFailure(call: Call, e: IOException) {
                    Log.d(TAG, "onFailure()11 called with: call = $call, e = $e")
                }

                override fun onResponse(call: Call, response: Response) {
                    Log.d(TAG, "onResponse()11 called with: call = $call, response = $response")
                }

            })
        }
        binding.button2.setOnClickListener {
            val client= OkHttpClient.Builder()
                .addNetworkInterceptor(LoggingInterceptor())
                .build()
            val request = Request.Builder()
                .url("http://www.publicobject.com/helloworld.txt")
                .header("Name", "OkHttp Example")
                .build()

            client.newCall(request).enqueue(object:Callback{
                override fun onFailure(call: Call, e: IOException) {
                    Log.d(TAG, "onFailure()22 called with: call = $call, e = $e")
                }

                override fun onResponse(call: Call, response: Response) {
                    Log.d(TAG, "onResponse()22 called with: call = $call, response = $response")
                }

            })
        }
    }

    internal class LoggingInterceptor : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            val request: Request = chain.request()
            val t1 = System.nanoTime()
            Log.i(OkHttpActivity::class.java.simpleName,
                String.format(
                    "Sending request %s on %s%n%s",
                    request.url, chain.connection(), request.headers
                )
            )
            val response: Response = chain.proceed(request)
            Log.i(OkHttpActivity::class.java.simpleName,
                String.format(
                    "Received response for %s,response code:%d %n%s",
                    response.request.url,response.code,response.headers
                )
            )
            return response
        }
    }

}