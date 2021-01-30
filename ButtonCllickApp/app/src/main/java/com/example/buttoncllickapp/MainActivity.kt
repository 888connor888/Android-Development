package com.example.buttoncllickapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private var button: Button? = null
    private var textView: TextView? = null
    private var numTimesClicked = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText:EditText? = findViewById<EditText>(R.id.editText)
        button = findViewById<Button>(R.id.button)
        textView = findViewById<TextView>(R.id.textView2)
        textView?.text = ""
        editText?.setText("")
        textView?.movementMethod = ScrollingMovementMethod()
        /*
        button?.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                numTimesClicked++
//                Toast.makeText(this@MainActivity,numTimesClicked.toString(),Toast.LENGTH_SHORT).show()
                textView?.append("\n Button Clicked $numTimesClicked times")
            }
        })
         */
        button?.setOnClickListener {
            numTimesClicked += 1
            val str:String = editText?.text.toString()
            val msg:String = "$numTimesClicked. $str\n"
            textView?.append(msg)
            editText?.setText("")
        }
    }
}