package com.example.workmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.workmanager.response.User
import com.example.workmanager.rv.UserAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val adapter = UserAdapter()
    var originalList = arrayListOf<User>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adapter.onItemClick = {
            val intent = Intent(this,UserActivity::class.java)
            intent.putExtra("ID",it.toString())
            startActivity(intent)
        }
        userRv.apply {
            layoutManager =LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }
    }
}