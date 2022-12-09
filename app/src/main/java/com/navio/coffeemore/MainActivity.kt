package com.navio.coffeemore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.navio.coffeemore.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val buttonOne = binding.buttonOne
        val buttonTwo = binding.buttonTwo
        val buttonThree = binding.buttonThree

        //Load coffee counter
        buttonOne.setOnClickListener {

            val switchActivityIntent = Intent(this, CoffeeCounter::class.java)
            startActivity(switchActivityIntent)
//            finish()
        }
//        //Load web viewer
        buttonTwo.setOnClickListener {

            val switchActivityIntent = Intent(this, LauncherWebView::class.java)
            startActivity(switchActivityIntent)
//            finish()
        }
//        //Load primos
        buttonThree.setOnClickListener {

            val switchActivityIntent = Intent(this, PrimeNumbers::class.java)
            startActivity(switchActivityIntent)
//            finish()
        }
    }
}