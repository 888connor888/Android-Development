package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener

//import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val textView = findViewById<TextView>(R.id.textView)
        val editText = findViewById<EditText>(R.id.editText)
        val button = findViewById<Button>(R.id.button)
        with(textView){
            text = "Hello H3RMIT"
            setTextColor(getColor(R.color.design_default_color_primary_dark))
            textSize = 40f
        }
        // Or you can also use apply function
       editText.apply {
           hint = "Enter Your Name"
           setText("Hello There")
           addTextChangedListener {
               Log.i("TAG",it.toString())
           }
       }
       button.setOnClickListener {
           Toast.makeText(this@MainActivity,editText.text.toString(),Toast.LENGTH_SHORT).show()
       }
    }
}