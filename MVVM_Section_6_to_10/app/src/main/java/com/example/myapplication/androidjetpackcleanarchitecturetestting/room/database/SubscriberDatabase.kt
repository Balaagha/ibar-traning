package com.example.myapplication.androidjetpackcleanarchitecturetestting.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Subscriber::class],version = 1)
abstract class SubscriberDatabase: RoomDatabase() {
    abstract val subscriberDao: SubscriberDao
    companion object{
        @Volatile // Volatile annotation makes the field immediately made visible to other threads.
        private var INSTANCE: SubscriberDatabase? = null
        fun getInstance(context: Context): SubscriberDatabase{
            synchronized(this){
                var instance = INSTANCE
                if(instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        SubscriberDatabase::class.java,
                        "subscriber_data_table"
                    ).build()
                }
                return instance
            }
        }
    }
}