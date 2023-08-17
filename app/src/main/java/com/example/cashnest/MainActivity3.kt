package com.example.cashnest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        val amount = intent.getIntExtra("predictionAmount", 0)
        val textView: TextView = findViewById(R.id.textView)
        textView.text = "₩ $amount"
    }
}