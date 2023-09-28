package com.example.connectapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextClock
import android.widget.TextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btNewJoke = findViewById<Button>(R.id.btNewJoke)

        btNewJoke.setOnClickListener{
            loadJoke()
        }
    }

    private fun loadJoke() {
        //https://geek-jokes.sameerkumar.website/api?format=json

        val tvJoke = findViewById<TextView>(R.id.tvJoke)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://geek-jokes.sameerkumar.website/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        val jokeService: JokeService
        jokeService = retrofit.create(JokeService::class.java)

        val request = jokeService.getJoke("json")

        request.enqueue(object: Callback<Joke>{
            override fun onResponse(call: Call<Joke>, response: Response<Joke>) {
                if(response.isSuccessful){
                    tvJoke.text = response.body()?.joke
                }
            }

            override fun onFailure(call: Call<Joke>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })

    }
}