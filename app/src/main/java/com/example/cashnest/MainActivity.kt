package com.example.cashnest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.content.Intent
import android.provider.Settings.Global
import android.widget.Button
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import com.github.mikephil.charting.charts.BarChart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import com.example.cashnest.MainActivity2
//성공주석
class MainActivity : AppCompatActivity() {

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://172.30.1.46:5000")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val apiService = retrofit.create(ApiService::class.java)
    private val apiService2 = retrofit.create(ApiService2::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button1: ImageButton = findViewById(R.id.button1)

        button1.setOnClickListener {
            val intent2 = Intent(this, MainActivity2::class.java)
            GlobalScope.launch(Dispatchers.IO) {
                val response = apiService2.getPrediction()

                if (response.isSuccessful) {
                    val predictionData: PredictionData? = response.body()
                    MainActivity2.one = predictionData?.category01 ?: 0
                    MainActivity2.two = predictionData?.category02 ?: 0
                    MainActivity2.three = predictionData?.category03 ?: 0
                    MainActivity2.four = predictionData?.category04 ?: 0
                    MainActivity2.five = predictionData?.category05 ?: 0
                    MainActivity2.six = predictionData?.category06 ?: 0
                    MainActivity2.seven = predictionData?.category07 ?: 0
                    MainActivity2.eight = predictionData?.category08 ?: 0
                    MainActivity2.nine = predictionData?.category09 ?: 0
                    MainActivity2.ten = predictionData?.category10 ?: 0
                    MainActivity2.eleven = predictionData?.category11 ?: 0
                    MainActivity2.twelve = predictionData?.category12 ?: 0
                    MainActivity2.thirteen = predictionData?.category13 ?: 0
                    MainActivity2.fourteen = predictionData?.category14 ?: 0
                    MainActivity2.fifteen = predictionData?.category15 ?: 0
                    MainActivity2.sixteen = predictionData?.category16 ?: 0
                    startActivity(intent2)
                } else {

                }
            }

            val button2: ImageButton = findViewById(R.id.button2)
            button2.setOnClickListener {
                val intent = Intent(this, MainActivity3::class.java)
                val call = apiService.getPrediction()
                call.enqueue(object : Callback<Map<String, Int>> {
                    override fun onResponse(
                        call: Call<Map<String, Int>>,
                        response: Response<Map<String, Int>>
                    ) {
                        if (response.isSuccessful) {
                            val predictionAmount = response.body()?.get("amount")
                            intent.putExtra("predictionAmount", predictionAmount)
                            startActivity(intent)
                        }
                    }

                    override fun onFailure(call: Call<Map<String, Int>>, t: Throwable) {
                        Toast.makeText(this@MainActivity, "Error: ${t.message}", Toast.LENGTH_LONG)
                            .show()
                    }
                })
            }
        }
    }
}