package com.example.myapplication.androidjetpackcleanarchitecturetestting.room.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface SubscriberDao {
//    @Insert(onConflict = OnConflictStrategy.REPLACE) => This will try insert the entity, and if there is an existing row that has same ID, it will delete the old entity and replace.
//    @Insert(onConflict = OnConflictStrategy.IGNORE) => It will ignore the complicit

    @Insert(onConflict = OnConflictStrategy.REPLACE )
    suspend fun insertSubscriber(subscriber: Subscriber): Long

    @Update
    suspend fun updateSubscriber(subscriber: Subscriber): Int

    @Delete
    suspend fun deleteSubscriber(subscriber: Subscriber): Int

    @Query("DELETE FROM subscriber_data_table")
    suspend fun deleteAll(): Int

    // In below case, we don't add suspend, because we don't need to execute this function in a background thread using coroutine. Since this function return LiveData, room library do its work from a background thread, So, this is our Data Access Object interface.
    @Query("SELECT * FROM subscriber_data_table")
    fun getAllSubscribers(): LiveData<List<Subscriber >>
}