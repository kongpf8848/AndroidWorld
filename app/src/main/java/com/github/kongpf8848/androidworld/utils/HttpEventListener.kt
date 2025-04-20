package com.github.kongpf8848.androidworld.utils

import android.util.Log
import okhttp3.Call
import okhttp3.Connection
import okhttp3.EventListener
import okhttp3.Handshake
import okhttp3.HttpUrl
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import java.net.InetAddress
import java.net.InetSocketAddress
import java.net.Proxy

class HttpEventListener: EventListener() {
    private  val TAG = "HttpEventListener"
    override fun callFailed(call: Call, ioe: IOException) {
        super.callFailed(call, ioe)
        Log.d(TAG, "callFailed() called with: call = $call, ioe = $ioe")
    }

    override fun callEnd(call: Call) {
        super.callEnd(call)
        Log.d(TAG, "callEnd() called with: call = $call")
    }

    override fun callStart(call: Call) {
        super.callStart(call)
        Log.d(TAG, "callStart() called with: call = $call")
    }

    override fun canceled(call: Call) {
        super.canceled(call)
        Log.d(TAG, "canceled() called with: call = $call")
    }

    override fun connectEnd(
        call: Call,
        inetSocketAddress: InetSocketAddress,
        proxy: Proxy,
        protocol: Protocol?
    ) {
        super.connectEnd(call, inetSocketAddress, proxy, protocol)
        Log.d(
            TAG,
            "connectEnd() called with: call = $call, inetSocketAddress = $inetSocketAddress, proxy = $proxy, protocol = $protocol"
        )
    }

    override fun connectFailed(
        call: Call,
        inetSocketAddress: InetSocketAddress,
        proxy: Proxy,
        protocol: Protocol?,
        ioe: IOException
    ) {
        super.connectFailed(call, inetSocketAddress, proxy, protocol, ioe)
        Log.d(
            TAG,
            "connectFailed() called with: call = $call, inetSocketAddress = $inetSocketAddress, proxy = $proxy, protocol = $protocol, ioe = $ioe"
        )
    }

    override fun connectionAcquired(call: Call, connection: Connection) {
        super.connectionAcquired(call, connection)
        Log.d(TAG, "connectionAcquired() called with: call = $call, connection = $connection")
    }

    override fun connectStart(call: Call, inetSocketAddress: InetSocketAddress, proxy: Proxy) {
        super.connectStart(call, inetSocketAddress, proxy)
        Log.d(
            TAG,
            "connectStart() called with: call = $call, inetSocketAddress = $inetSocketAddress, proxy = $proxy"
        )
    }

    override fun connectionReleased(call: Call, connection: Connection) {
        super.connectionReleased(call, connection)
        Log.d(TAG, "connectionReleased() called with: call = $call, connection = $connection")
    }

    override fun dnsEnd(call: Call, domainName: String, inetAddressList: List<InetAddress>) {
        super.dnsEnd(call, domainName, inetAddressList)
        Log.d(
            TAG,
            "dnsEnd() called with: call = $call, domainName = $domainName, inetAddressList = $inetAddressList"
        )
    }

    override fun dnsStart(call: Call, domainName: String) {
        super.dnsStart(call, domainName)
        Log.d(TAG, "dnsStart() called with: call = $call, domainName = $domainName")
    }

    override fun proxySelectEnd(call: Call, url: HttpUrl, proxies: List<Proxy>) {
        super.proxySelectEnd(call, url, proxies)
        Log.d(TAG, "proxySelectEnd() called with: call = $call, url = $url, proxies = $proxies")
    }

    override fun proxySelectStart(call: Call, url: HttpUrl) {
        super.proxySelectStart(call, url)
        Log.d(TAG, "proxySelectStart() called with: call = $call, url = $url")
    }

    override fun requestBodyEnd(call: Call, byteCount: Long) {
        super.requestBodyEnd(call, byteCount)
        Log.d(TAG, "requestBodyEnd() called with: call = $call, byteCount = $byteCount")
    }

    override fun requestBodyStart(call: Call) {
        super.requestBodyStart(call)
        Log.d(TAG, "requestBodyStart() called with: call = $call")
    }

    override fun requestFailed(call: Call, ioe: IOException) {
        super.requestFailed(call, ioe)
        Log.d(TAG, "requestFailed() called with: call = $call, ioe = $ioe")
    }

    override fun requestHeadersEnd(call: Call, request: Request) {
        super.requestHeadersEnd(call, request)
        Log.d(TAG, "requestHeadersEnd() called with: call = $call, request = $request")
    }

    override fun requestHeadersStart(call: Call) {
        super.requestHeadersStart(call)
        Log.d(TAG, "requestHeadersStart() called with: call = $call")
    }

    override fun responseBodyEnd(call: Call, byteCount: Long) {
        super.responseBodyEnd(call, byteCount)
        Log.d(TAG, "responseBodyEnd() called with: call = $call, byteCount = $byteCount")
    }

    override fun responseBodyStart(call: Call) {
        super.responseBodyStart(call)
        Log.d(TAG, "responseBodyStart() called with: call = $call")
    }

    override fun responseFailed(call: Call, ioe: IOException) {
        super.responseFailed(call, ioe)
        Log.d(TAG, "responseFailed() called with: call = $call, ioe = $ioe")
    }

    override fun responseHeadersEnd(call: Call, response: Response) {
        super.responseHeadersEnd(call, response)
        Log.d(TAG, "responseHeadersEnd() called with: call = $call, response = $response")
    }

    override fun responseHeadersStart(call: Call) {
        super.responseHeadersStart(call)
        Log.d(TAG, "responseHeadersStart() called with: call = $call")
    }

    override fun satisfactionFailure(call: Call, response: Response) {
        super.satisfactionFailure(call, response)
        Log.d(TAG, "satisfactionFailure() called with: call = $call, response = $response")
    }

    override fun secureConnectEnd(call: Call, handshake: Handshake?) {
        super.secureConnectEnd(call, handshake)
        Log.d(TAG, "secureConnectEnd() called with: call = $call, handshake = $handshake")
    }

    override fun secureConnectStart(call: Call) {
        super.secureConnectStart(call)
        Log.d(TAG, "secureConnectStart() called with: call = $call")
    }
}