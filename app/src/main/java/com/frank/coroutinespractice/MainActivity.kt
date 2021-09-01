package com.frank.coroutinespractice

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.frank.coroutinespractice.home.HomeFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(null == savedInstanceState){
            supportFragmentManager.beginTransaction().add(R.id.container, HomeFragment())
                .addToBackStack("home".javaClass.name).commit()
        }
    }
}