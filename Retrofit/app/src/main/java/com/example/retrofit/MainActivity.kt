package com.example.retrofit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO

class MainActivity : AppCompatActivity() {
    private val tag = "IntentDebug"
    private val adapter = UserAdapter()
    val originalList = arrayListOf<User>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adapter.onItemClick = {
            val intent = Intent(this,UserActivity::class.java)
            Log.d(tag,it)
            intent.putExtra("ID",it.toString())
            startActivity(intent)
        }
        userRv.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }

        searchView.isSubmitButtonEnabled = true
        searchView.setOnQueryTextListener(object:SearchView.OnQueryTextListener, androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { searchUsers(it) }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { searchUsers(it) }
                return true
            }
        })
        searchView.setOnCloseListener {
            adapter.swapData(originalList)
            true
        }

        //Populating the recycler view for the first time
        GlobalScope.launch(Dispatchers.Main) {
            val response = withContext(IO){Client.api.getUsers()}
            if(response.isSuccessful){
                response.body()?.let {
                    originalList.addAll(it)
                    adapter.swapData(it)
                }
            }
        }
    }

    fun searchUsers(query:String){
        GlobalScope.launch(Dispatchers.Main) {
            val response = withContext(IO){Client.api.searchUsers(query)}
            if(response.isSuccessful){
                response.body()?.let {
                    it.items?.let { it1 -> adapter.swapData(it1) }
                }
            }
        }
    }
}