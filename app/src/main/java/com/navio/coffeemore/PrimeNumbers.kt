package com.navio.coffeemore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class PrimeNumbers : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prime_numbers)

        val fieldNumbers = findViewById<EditText>(R.id.fieldNumber)
        val labelOutput = findViewById<TextView>(R.id.labelOutput)
        val buttonFind = findViewById<Button>(R.id.buttonFind)

        buttonFind.setOnClickListener {

            if (!fieldNumbers.text.isEmpty()) {

                val numberGiven: Int = fieldNumbers.text.toString().toInt()
                val n = inferiorPrimes(fieldNumbers.text.toString().toInt()).toString()
                labelOutput.text = n
                Log.d("Primes", n)
            } else {
                Toast.makeText(
                    this, "Introduce un número.", Toast.LENGTH_SHORT
                ).show()
            }


        }

    }

    fun inferiorPrimes(number: Int): List<Int> {

        val list = mutableListOf<Int>()
        var currentNumber = number - 1

        while (currentNumber > 1) {

            if (isPrime(currentNumber))
                list.add(currentNumber)

            currentNumber--
        }
        return list
    }

    fun isPrime(number: Int): Boolean {

        var currentNumber = number - 1
        while (currentNumber > 1) {


            if (number % currentNumber == 0) {

                return false
            }
            currentNumber--
        }

        return true
    }
}