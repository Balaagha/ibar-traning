package com.example.myapplication.androidjetpackcleanarchitecturetestting.room.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.androidjetpackcleanarchitecturetestting.room.adapter.SubscriberAdapter
import com.example.myapplication.androidjetpackcleanarchitecturetestting.room.database.Subscriber
import com.example.myapplication.androidjetpackcleanarchitecturetestting.room.database.SubscriberDatabase
import com.example.myapplication.androidjetpackcleanarchitecturetestting.room.repository.SubscriberRepository
import com.example.myapplication.androidjetpackcleanarchitecturetestting.room.viewmodel.SubscriberViewModel
import com.example.myapplication.androidjetpackcleanarchitecturetestting.room.viewmodel.SubscriberViewModelFactory
import com.example.myapplication.databinding.ActivityRoomIntroBinding

class RoomIntroActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRoomIntroBinding
    private lateinit var subscriberViewModel: SubscriberViewModel
    private lateinit var adapter: SubscriberAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_room_intro)

        val dao = SubscriberDatabase.getInstance(application).subscriberDao
        val repository = SubscriberRepository(dao)

        val factory = SubscriberViewModelFactory(repository)
        subscriberViewModel = ViewModelProvider(this,factory).get(SubscriberViewModel::class.java)
        binding.viewModel = subscriberViewModel

        binding.lifecycleOwner = this

        initRecyclerView()

        subscriberViewModel.statusMessage.observe(this, Observer {
            it.getContentIfNotHandled()?.let{
                Toast.makeText(this,it,Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun initRecyclerView(){
        binding.rvRoom.layoutManager = LinearLayoutManager(this)
        adapter = SubscriberAdapter { selectedItem: Subscriber -> listItemClicked(selectedItem) }
        binding.rvRoom.adapter = adapter
        displaySubscribersList()
    }

    private fun displaySubscribersList(){
        subscriberViewModel.subscribers.observe(this, Observer {
            adapter.setList(it)
            adapter.notifyDataSetChanged()
        })
    }

    private fun listItemClicked(subscriber: Subscriber){
        Toast.makeText(this,"Selected name is ${subscriber.name}",Toast.LENGTH_LONG).show()
        subscriberViewModel.initUpdateAndDelete(subscriber)
    }
}