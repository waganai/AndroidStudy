package com.example.jetpackdemeo.webview

import android.graphics.Bitmap
import android.net.Uri
import android.net.http.SslError
import android.os.Bundle
import android.os.Message
import android.view.KeyEvent
import android.view.View
import android.webkit.*
import androidx.appcompat.app.AppCompatActivity
import com.example.jetpackdemeo.databinding.ActivityWebviewLayoutBinding

class WebViewActivity : AppCompatActivity() {

    companion object {
        const val LOAD_URL = "LOAD_URL"
    }

    var viewBindings: ActivityWebviewLayoutBinding? = null

    private val mLoadUrl by lazy {
        intent.getStringExtra(LOAD_URL)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBindings = ActivityWebviewLayoutBinding.inflate(layoutInflater)

        setContentView(viewBindings?.root)

        init()
    }

    private fun init() {
        viewBindings?.webContent?.apply {
            webChromeClient = object : WebChromeClient() {
                override fun onProgressChanged(view: WebView?, newProgress: Int) {
                    super.onProgressChanged(view, newProgress)
                }

                override fun onReceivedTitle(view: WebView?, title: String?) {
                    super.onReceivedTitle(view, title)
                }

                override fun onReceivedIcon(view: WebView?, icon: Bitmap?) {
                    super.onReceivedIcon(view, icon)
                }

                override fun onReceivedTouchIconUrl(
                    view: WebView?,
                    url: String?,
                    precomposed: Boolean
                ) {
                    super.onReceivedTouchIconUrl(view, url, precomposed)
                }

                override fun onShowCustomView(view: View?, callback: CustomViewCallback?) {
                    super.onShowCustomView(view, callback)
                }

                override fun onShowCustomView(
                    view: View?,
                    requestedOrientation: Int,
                    callback: CustomViewCallback?
                ) {
                    super.onShowCustomView(view, requestedOrientation, callback)
                }

                override fun onHideCustomView() {
                    super.onHideCustomView()
                }

                override fun onCreateWindow(
                    view: WebView?,
                    isDialog: Boolean,
                    isUserGesture: Boolean,
                    resultMsg: Message?
                ): Boolean {
                    return super.onCreateWindow(view, isDialog, isUserGesture, resultMsg)
                }

                override fun onRequestFocus(view: WebView?) {
                    super.onRequestFocus(view)
                }

                override fun onCloseWindow(window: WebView?) {
                    super.onCloseWindow(window)
                }

                override fun onJsAlert(
                    view: WebView?,
                    url: String?,
                    message: String?,
                    result: JsResult?
                ): Boolean {
                    return super.onJsAlert(view, url, message, result)
                }

                override fun onJsConfirm(
                    view: WebView?,
                    url: String?,
                    message: String?,
                    result: JsResult?
                ): Boolean {
                    return super.onJsConfirm(view, url, message, result)
                }

                override fun onJsPrompt(
                    view: WebView?,
                    url: String?,
                    message: String?,
                    defaultValue: String?,
                    result: JsPromptResult?
                ): Boolean {
                    return super.onJsPrompt(view, url, message, defaultValue, result)
                }

                override fun onJsBeforeUnload(
                    view: WebView?,
                    url: String?,
                    message: String?,
                    result: JsResult?
                ): Boolean {
                    return super.onJsBeforeUnload(view, url, message, result)
                }

                override fun onExceededDatabaseQuota(
                    url: String?,
                    databaseIdentifier: String?,
                    quota: Long,
                    estimatedDatabaseSize: Long,
                    totalQuota: Long,
                    quotaUpdater: WebStorage.QuotaUpdater?
                ) {
                    super.onExceededDatabaseQuota(
                        url,
                        databaseIdentifier,
                        quota,
                        estimatedDatabaseSize,
                        totalQuota,
                        quotaUpdater
                    )
                }

                override fun onReachedMaxAppCacheSize(
                    requiredStorage: Long,
                    quota: Long,
                    quotaUpdater: WebStorage.QuotaUpdater?
                ) {
                    super.onReachedMaxAppCacheSize(requiredStorage, quota, quotaUpdater)
                }

                override fun onGeolocationPermissionsShowPrompt(
                    origin: String?,
                    callback: GeolocationPermissions.Callback?
                ) {
                    super.onGeolocationPermissionsShowPrompt(origin, callback)
                }

                override fun onGeolocationPermissionsHidePrompt() {
                    super.onGeolocationPermissionsHidePrompt()
                }

                override fun onPermissionRequest(request: PermissionRequest?) {
                    super.onPermissionRequest(request)
                }

                override fun onPermissionRequestCanceled(request: PermissionRequest?) {
                    super.onPermissionRequestCanceled(request)
                }

                override fun onJsTimeout(): Boolean {
                    return super.onJsTimeout()
                }

                override fun onConsoleMessage(
                    message: String?,
                    lineNumber: Int,
                    sourceID: String?
                ) {
                    super.onConsoleMessage(message, lineNumber, sourceID)
                }

                override fun onConsoleMessage(consoleMessage: ConsoleMessage?): Boolean {
                    return super.onConsoleMessage(consoleMessage)
                }

                override fun getDefaultVideoPoster(): Bitmap? {
                    return super.getDefaultVideoPoster()
                }

                override fun getVideoLoadingProgressView(): View? {
                    return super.getVideoLoadingProgressView()
                }

                override fun getVisitedHistory(callback: ValueCallback<Array<String>>?) {
                    super.getVisitedHistory(callback)
                }

                override fun onShowFileChooser(
                    webView: WebView?,
                    filePathCallback: ValueCallback<Array<Uri>>?,
                    fileChooserParams: FileChooserParams?
                ): Boolean {
                    return super.onShowFileChooser(webView, filePathCallback, fileChooserParams)
                }
            }

            webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                    return super.shouldOverrideUrlLoading(view, url)
                }

                override fun shouldOverrideUrlLoading(
                    view: WebView?,
                    request: WebResourceRequest?
                ): Boolean {
                    return super.shouldOverrideUrlLoading(view, request)
                }

                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    super.onPageStarted(view, url, favicon)
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                }

                override fun onLoadResource(view: WebView?, url: String?) {
                    super.onLoadResource(view, url)
                }

                override fun onPageCommitVisible(view: WebView?, url: String?) {
                    super.onPageCommitVisible(view, url)
                }

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

                override fun onTooManyRedirects(
                    view: WebView?,
                    cancelMsg: Message?,
                    continueMsg: Message?
                ) {
                    super.onTooManyRedirects(view, cancelMsg, continueMsg)
                }

                override fun onReceivedError(
                    view: WebView?,
                    errorCode: Int,
                    description: String?,
                    failingUrl: String?
                ) {
                    super.onReceivedError(view, errorCode, description, failingUrl)
                }

                override fun onReceivedError(
                    view: WebView?,
                    request: WebResourceRequest?,
                    error: WebResourceError?
                ) {
                    super.onReceivedError(view, request, error)
                }

                override fun onReceivedHttpError(
                    view: WebView?,
                    request: WebResourceRequest?,
                    errorResponse: WebResourceResponse?
                ) {
                    super.onReceivedHttpError(view, request, errorResponse)
                }

                override fun onFormResubmission(
                    view: WebView?,
                    dontResend: Message?,
                    resend: Message?
                ) {
                    super.onFormResubmission(view, dontResend, resend)
                }

                override fun doUpdateVisitedHistory(
                    view: WebView?,
                    url: String?,
                    isReload: Boolean
                ) {
                    super.doUpdateVisitedHistory(view, url, isReload)
                }

                override fun onReceivedSslError(
                    view: WebView?,
                    handler: SslErrorHandler?,
                    error: SslError?
                ) {
                    super.onReceivedSslError(view, handler, error)
                }

                override fun onReceivedClientCertRequest(
                    view: WebView?,
                    request: ClientCertRequest?
                ) {
                    super.onReceivedClientCertRequest(view, request)
                }

                override fun onReceivedHttpAuthRequest(
                    view: WebView?,
                    handler: HttpAuthHandler?,
                    host: String?,
                    realm: String?
                ) {
                    super.onReceivedHttpAuthRequest(view, handler, host, realm)
                }

                override fun shouldOverrideKeyEvent(view: WebView?, event: KeyEvent?): Boolean {
                    return super.shouldOverrideKeyEvent(view, event)
                }

                override fun onUnhandledKeyEvent(view: WebView?, event: KeyEvent?) {
                    super.onUnhandledKeyEvent(view, event)
                }

                override fun onScaleChanged(view: WebView?, oldScale: Float, newScale: Float) {
                    super.onScaleChanged(view, oldScale, newScale)
                }

                override fun onReceivedLoginRequest(
                    view: WebView?,
                    realm: String?,
                    account: String?,
                    args: String?
                ) {
                    super.onReceivedLoginRequest(view, realm, account, args)
                }

                override fun onRenderProcessGone(
                    view: WebView?,
                    detail: RenderProcessGoneDetail?
                ): Boolean {
                    return super.onRenderProcessGone(view, detail)
                }

                override fun onSafeBrowsingHit(
                    view: WebView?,
                    request: WebResourceRequest?,
                    threatType: Int,
                    callback: SafeBrowsingResponse?
                ) {
                    super.onSafeBrowsingHit(view, request, threatType, callback)
                }
            }

            loadUrl(mLoadUrl ?: "https://www.baidu.com")
        }
    }

}