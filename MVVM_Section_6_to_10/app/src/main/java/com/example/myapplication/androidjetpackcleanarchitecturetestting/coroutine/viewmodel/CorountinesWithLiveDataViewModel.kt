package com.example.myapplication.androidjetpackcleanarchitecturetestting.coroutine.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.androidjetpackcleanarchitecturetestting.coroutine.model.User
import com.example.myapplication.androidjetpackcleanarchitecturetestting.coroutine.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CorountinesWithLiveDataViewModel: ViewModel() {
    private var userRepositery = UserRepository()
    // First way
//    var users: MutableLiveData<List<User>> = MutableLiveData()
//
//    fun getUsers(){
//        viewModelScope.launch {
//            var result: List<User>? = null
//            withContext(Dispatchers.IO){
//                result = userRepositery.getUsers()
//            }
//            users.value = result
//        }
//    }
    // Second Way
    var users = liveData(Dispatchers.IO) {
        val result =  userRepositery.getUsers()
        emit(result)
    }
}