package com.example.myapplication.androidjetpackcleanarchitecturetestting.coroutine.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.androidjetpackcleanarchitecturetestting.coroutine.model.User
import com.example.myapplication.androidjetpackcleanarchitecturetestting.coroutine.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CoroutineWithViewModel: ViewModel() {
    private var userRepository = UserRepository()
    private var _users: MutableLiveData<List<User>> = MutableLiveData()
    val user: LiveData<List<User>> get() = _users

    fun getUserData(){
        viewModelScope.launch {
            var result: List<User>? = null
            withContext(Dispatchers.IO){
                result = userRepository.getUsers()
            }
            _users.value = result
        }
    }

    override fun onCleared() { // In Android viewModel is cleared from the memory, just before clearing, viewModel invokes its onCleared() method.
        super.onCleared()
    }

}