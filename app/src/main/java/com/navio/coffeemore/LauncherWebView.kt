package com.navio.coffeemore

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity


class LauncherWebView : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launcher_web_view)

        val fieldSearch = findViewById<EditText>(R.id.fieldURL)
        val buttonSearch = findViewById<Button>(R.id.buttonSearch)

        buttonSearch.setOnClickListener{

            val value = fieldSearch.text.toString()
            val intent = Intent(this, WebView::class.java)
            intent.putExtra("url", value)
            startActivity(intent)
        }
    }
}