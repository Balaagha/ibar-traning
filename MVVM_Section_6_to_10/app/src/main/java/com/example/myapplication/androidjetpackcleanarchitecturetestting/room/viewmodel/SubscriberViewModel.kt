package com.example.myapplication.androidjetpackcleanarchitecturetestting.room.viewmodel

import android.util.Patterns
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.androidjetpackcleanarchitecturetestting.room.database.Subscriber
import com.example.myapplication.androidjetpackcleanarchitecturetestting.room.evet.Event
import com.example.myapplication.androidjetpackcleanarchitecturetestting.room.repository.SubscriberRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.regex.Pattern

class SubscriberViewModel(val repository: SubscriberRepository): ViewModel(),Observable {
    // To use these Bindable tags in viewModel, this viewModel class should implement the Observable interface. This should be androidx.databinding.Observable (Nor the java.util.Observable).

    val subscribers = repository.subscribers

    private var isUpdateOrDelete = false
    private lateinit var subscriberToUpdateOrDelete: Subscriber

    @Bindable
    val inputName = MutableLiveData<String>()
    @Bindable
    val inputEmail = MutableLiveData<String>()
    @Bindable
    val saveOrUpdateButtonText = MutableLiveData<String>()
    @Bindable
    val clearAllOrDeleteButtonText = MutableLiveData<String>()

    // For Event
    private val _statusMessage = MutableLiveData<Event<String>>()
    val statusMessage: LiveData<Event<String>> get() = _statusMessage
    init {
        changeUpdateOrDeleteState(false)
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    fun saveOrUpdate(){
        validateInputs{
            if (isUpdateOrDelete){
                subscriberToUpdateOrDelete.name = inputName.value!!
                subscriberToUpdateOrDelete.email = inputEmail.value!!
                update(subscriberToUpdateOrDelete)
            }else{
                insert(Subscriber(0,inputName.value!!,inputEmail.value!!))
            }
        }
    }
    fun clearAllOrDelete(){
        if(isUpdateOrDelete){
            delete(subscriberToUpdateOrDelete)
        }else{
            clearAll()
        }
    }

    fun initUpdateAndDelete(subscriber: Subscriber){
        inputEmail.value = subscriber.email
        inputName.value = subscriber.name
        changeUpdateOrDeleteState(true)
        subscriberToUpdateOrDelete = subscriber
    }


    fun insert(subscriber: Subscriber) = viewModelScope.launch {
        delay(4000)
        val newRowId = repository.insert(subscriber)
        makeInputsValueNull()
        if(newRowId > -1){
            _statusMessage.value = Event("Subscriber Inserted Successfully (rowId => $newRowId)")
        }else{
            _statusMessage.value = Event("Error Occurred")
        }
    }
    fun update(subscriber: Subscriber) = viewModelScope.launch {
        delay(4000)
        val noOfRow = repository.update(subscriber)
        if(noOfRow > 0){
            changeUpdateOrDeleteState(false)
            makeInputsValueNull()
            _statusMessage.value = Event("$noOfRow Row Updated Successfully")
        }else{
            _statusMessage.value = Event("Error Occurred")
        }
    }
    fun delete(subscriber: Subscriber) = viewModelScope.launch {
        delay(4000)
        val noOfRowDeleted = repository.delete(subscriber)
        if(noOfRowDeleted > 0){
            changeUpdateOrDeleteState(false)
            makeInputsValueNull()
            _statusMessage.value = Event("$noOfRowDeleted Row Deleted Successfully")
        }else{
            _statusMessage.value = Event("Error Occurred")
        }
    }
    fun clearAll() = viewModelScope.launch {
        val noOfRowsDeleted = repository.deleteAll()
        if(noOfRowsDeleted > 0){
            _statusMessage.value = Event("$noOfRowsDeleted Subscribers Deleted Successfully")
        }else{
            _statusMessage.value = Event("Error Occurred")
        }
    }

// Helper function
    private fun changeUpdateOrDeleteState(isUpdateOrDeleteState: Boolean){
        isUpdateOrDelete = isUpdateOrDeleteState
        if (isUpdateOrDeleteState){
            saveOrUpdateButtonText.value = "Update"
            clearAllOrDeleteButtonText.value = "Delete"
        }else{
            saveOrUpdateButtonText.value = "Save"
            clearAllOrDeleteButtonText.value = "Clear All"
        }
    }
    private fun makeInputsValueNull(){
        inputName.value = null
        inputEmail.value = null
    }
    private inline fun validateInputs(operation: () -> Unit){
        if (inputName.value.isNullOrBlank()){
            _statusMessage.value = Event("Please enter subscriber's name")
        }else if (inputEmail.value.isNullOrBlank()){
            _statusMessage.value = Event("Please enter subscriber's email")
        }else if(!Patterns.EMAIL_ADDRESS.matcher(inputEmail.value!!).matches()){
            _statusMessage.value = Event("Please enter a correct email address")
        }
        else{
            operation()
        }
    }

}