package com.navio.coffeemore

import android.os.Bundle
import android.util.Log
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.navio.coffeemore.databinding.ActivityWebViewBinding

class WebView : AppCompatActivity() {

    private lateinit var binding: ActivityWebViewBinding
    private lateinit var webView: android.webkit.WebView

    private val DEFAULT_URL = "https://google.com"
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        webView = binding.webView

        //Load clients
        webView.webChromeClient = object : WebChromeClient() {} //Or WebChromeClient()
        webView.webViewClient = object : WebViewClient() {}

        //Activate javascript
        val settings = webView.settings
        settings.javaScriptEnabled = true

        //Retrieve values from intent launcher
        val arg : Bundle? = intent.extras
        if (arg != null) {
            val url : String? = arg.getString("url")//The key argument here must match that used in the other activity
            Log.d("Intent", "Arg passed...")
            webView.loadUrl("https://" + url.toString())
        }else{
            webView.loadUrl(DEFAULT_URL)
        }
    }
}