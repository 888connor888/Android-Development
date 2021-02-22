package com.example.mvvm.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvm.R
import com.example.mvvm.data.models.User
import com.example.mvvm.ui.adapter.UserAdapter
import com.example.mvvm.ui.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val vm by lazy {
        ViewModelProvider(this).get(MainActivityViewModel::class.java)
    }
    private var list = arrayListOf<User>()
    private var originalList = arrayListOf<User>()
    private val adapter = UserAdapter(list)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        userRv.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }
        searchView.isSubmitButtonEnabled = true
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let{
                    findUsers(it)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let{
                    findUsers(it)
                }
                return true
            }

        })
        searchView.setOnCloseListener {
            list.clear()
            list.addAll(originalList)
            adapter.notifyDataSetChanged()
            return@setOnCloseListener true
        }
        vm.fetchUsers()
        vm.users.observe(this, Observer {
            if(!it.isNullOrEmpty()){
                list.addAll(it)
                originalList.addAll(it)
                adapter.notifyDataSetChanged()
            }
        })
    }

    private fun findUsers(query: String) {
        vm.searchUsers(query).observe(this@MainActivity, Observer {
            list.clear()
            list.addAll(it!!)
            adapter.notifyDataSetChanged()
        })

    }
}