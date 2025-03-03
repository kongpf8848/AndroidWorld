package com.github.kongpf8848.androidworld;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import okhttp3.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 * <p>
 * See [testing documentation](http://d.android.com/tools/testing).
 * <p>
 * ++++++++++++OkHttp:
 * <p>
 * 支持 HTTP/2 和 SPDY，允许所有请求共享同一个 Socket。
 * <p>
 * 内置连接池，减少请求延迟。
 * <p>
 * 支持 GZIP 压缩，减少传输数据量。
 * <p>
 * 支持缓存，减少重复请求。
 * <p>
 * 提供同步和异步请求方式。
 * <p>
 * 支持拦截器，方便扩展和定制。
 * <p>
 * ++++++++++++ OkHttp五大拦截器 ++++++++++++
 * <p>
 * {@link okhttp3.internal.http.RetryAndFollowUpInterceptor}: 负责处理请求的重试和重定向
 * 功能：
 * 自动处理 HTTP 重定向（如 301、302、307 状态码）。
 * 在连接失败时自动重试请求。
 * <p>
 * {@link okhttp3.internal.http.BridgeInterceptor}: 添加请求头和处理响应头
 * 负责自动添加Content-Type、Content-Length、Host、Connection、User-Agent等请求头
 * 处理GZIP压缩响应，实现自动Gzip解压缩
 * <p>
 * {@link okhttp3.internal.cache.CacheInterceptor}: 处理HTTP缓存
 * 根据缓存策略（如 Cache-Control）决定是否使用缓存。
 * 缓存响应数据，减少重复请求。
 * <p>
 * {@link okhttp3.internal.connection.ConnectInterceptor}: 建立与服务器的连接
 * <p>
 * <p>
 * {@link okhttp3.internal.http.CallServerInterceptor}: 向服务器发送请求并读取响应
 * <p>
 * ++++++++++++ OkHttp Cache策略++++++++++++
 * 不缓存
 * {@link CacheControl.Builder#noCache()}
 *
 * 只使用缓存
 * {@link CacheControl.Builder#onlyIfCached()}
 *
 * 不存储服务端的缓存
 * {@link CacheControl.Builder#noStore()}
 */

public class OkHttpTest {

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);

    }
}