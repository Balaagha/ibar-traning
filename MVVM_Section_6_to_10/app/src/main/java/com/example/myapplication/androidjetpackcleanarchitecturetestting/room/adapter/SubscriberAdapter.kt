package com.example.myapplication.androidjetpackcleanarchitecturetestting.room.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.androidjetpackcleanarchitecturetestting.room.database.Subscriber
import com.example.myapplication.databinding.ListItemRoomIntroBinding

class SubscriberAdapter( private val clickListener: (Subscriber) -> Unit): RecyclerView.Adapter<SubscriberViewHolder>() {
    private val list = ArrayList<Subscriber>()
    fun setList(subscribers: List<Subscriber>){
        list.clear()
        list.addAll(subscribers)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubscriberViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ListItemRoomIntroBinding = DataBindingUtil.inflate(layoutInflater,R.layout.list_item_room_intro,parent,false)
        return SubscriberViewHolder(binding)

    }
    override fun getItemCount(): Int {
        return list.size
    }
    override fun onBindViewHolder(holder: SubscriberViewHolder, position: Int) {
        holder.bind(list[position],clickListener)
    }


}
class SubscriberViewHolder(val binding: ListItemRoomIntroBinding): RecyclerView.ViewHolder(binding.root){
    fun bind(subscriber: Subscriber, clickListener: (Subscriber) -> Unit){
        binding.tvName.text = subscriber.name
        binding.tvEmail.text = subscriber.email
        binding.lyListItem.setOnClickListener{
            clickListener(subscriber)
        }
    }
}