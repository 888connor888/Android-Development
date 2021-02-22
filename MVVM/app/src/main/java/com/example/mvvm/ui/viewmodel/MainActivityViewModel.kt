package com.example.mvvm.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.mvvm.data.models.User
import com.example.mvvm.data.repos.GithubRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO

class MainActivityViewModel: ViewModel() { // view models have inbuilt support for coroutines

    val users = MutableLiveData<List<User>>()
    fun fetchUsers(){
        viewModelScope.launch {
            val response = withContext(IO){GithubRepository.getUsers()}
            if(response.isSuccessful){
                response.body().let {
                    /*users.value = (it)  [Does every thing on the main thread & we'r on Default thread]*/
                    users.postValue(it)
                    Log.d("tag",it.toString())
                }
            }
        }
        //runIO(Main) {}
    }
    fun searchUsers(name:String) = liveData(Dispatchers.IO){
        val response = withContext(IO) {GithubRepository.searchUsers(name)}
        if(response.isSuccessful){
            response.body()?.let {
                emit(it.items)
            }
        }
    }
    /** Extension function for [ViewModel] scope*/
    private fun ViewModel.runIO(dispatcher:CoroutineDispatcher = Dispatchers.IO, function: suspend CoroutineScope.()->Unit){
        viewModelScope.launch(dispatcher) {
            function()
        }
    }
}