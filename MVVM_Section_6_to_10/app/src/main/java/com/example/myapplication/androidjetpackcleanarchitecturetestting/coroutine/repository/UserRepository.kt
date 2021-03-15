package com.example.myapplication.androidjetpackcleanarchitecturetestting.coroutine.repository

import com.example.myapplication.androidjetpackcleanarchitecturetestting.coroutine.model.User
import kotlinx.coroutines.delay

class UserRepository {

    suspend fun getUsers(): List<User>{
        delay(2000)
        val users: List<User> = listOf(
            User(1,"Aygun"),
            User(2,"Samir"),
            User(3,"Leyla"),
            User(4,"Orxan")
        )
        return users
    }

}