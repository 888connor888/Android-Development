package com.example.room.data

import androidx.lifecycle.LiveData

class UserRepository(private val userDao: UserDao) {
    val readAllData:LiveData<List<User>> = userDao.readAllUser()
    suspend fun addUser(user:User){
        userDao.addUser(user)
    }
}