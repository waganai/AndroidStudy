package com.example.jetpackdemeo.webview

import android.os.Bundle
import android.webkit.*
import androidx.appcompat.app.AppCompatActivity
import com.example.jetpackdemeo.databinding.ActivityWebviewLayoutBinding

class WebViewActivity : AppCompatActivity() {

    var viewBindings: ActivityWebviewLayoutBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBindings = ActivityWebviewLayoutBinding.inflate(layoutInflater)

        setContentView(viewBindings?.root)

        init()
    }

    private fun init() {
        viewBindings?.webContent?.apply {
            webChromeClient = object : WebChromeClient() {
            }

            webViewClient = object : WebViewClient() {
                override fun shouldInterceptRequest(
                    view: WebView?,
                    url: String?
                ): WebResourceResponse? {
                    return super.shouldInterceptRequest(view, url)
                }

                override fun shouldInterceptRequest(
                    view: WebView?,
                    request: WebResourceRequest?
                ): WebResourceResponse? {
                    return super.shouldInterceptRequest(view, request)
                }
            }

            loadUrl("https://www.bilibili.com")
        }
    }

}