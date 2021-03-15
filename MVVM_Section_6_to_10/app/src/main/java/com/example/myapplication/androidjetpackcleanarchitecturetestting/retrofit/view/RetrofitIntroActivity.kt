package com.example.myapplication.androidjetpackcleanarchitecturetestting.retrofit.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import com.example.myapplication.R
import com.example.myapplication.androidjetpackcleanarchitecturetestting.retrofit.model.AlbumItem
import com.example.myapplication.androidjetpackcleanarchitecturetestting.retrofit.model.Albums
import com.example.myapplication.androidjetpackcleanarchitecturetestting.retrofit.service.AlbumService
import com.example.myapplication.androidjetpackcleanarchitecturetestting.retrofit.service.RetrofitInstance
import kotlinx.android.synthetic.main.activity_retrofit_intro.*
import retrofit2.Response

class RetrofitIntroActivity : AppCompatActivity() {
    private lateinit var retService: AlbumService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit_intro)
        retService = RetrofitInstance.getRetrofitInstance().create(AlbumService::class.java)


//        getRequestWithPathParameters()
//        getRequestWithQueryParameters()
        uploadAlbum()

    }
    private fun getRequestWithQueryParameters(){
        // Example response general
        val responseLiveData: LiveData<Response<Albums>> = liveData {
            val response = retService.getAlbumsByUserId(3)
            emit(response)
        }
        responseLiveData.observe(this, Observer {
            val albumsList = it.body()?.listIterator()
            if(albumsList!=null){
                while (albumsList.hasNext()){
                    val albumItem = albumsList.next()
                    val result = " "+"Album Title: ${albumItem.title}"+"\n" +
                            " "+"Album id: ${albumItem.id}"+"\n" +
                            " "+"User id: ${albumItem.userId}"+"\n\n\n"
                    tvAlbums.append(result)
                }
            }
        })
    }

    private fun getRequestWithPathParameters(){
        // Path response example
        val pathResponse: LiveData<Response<AlbumItem>> = liveData {
            val response = retService.getAlbum(3)
            emit(response)
        }
        pathResponse.observe(this, Observer {
            val title = it.body()?.title + "\n\n\n"
            tvAlbums.text = title
        })
    }

    private fun uploadAlbum(){
        // Post request example
        val album = AlbumItem(1,"Custom Title",2)
        val postResponse: LiveData<Response<AlbumItem>> = liveData {
            val response = retService.uploadAlbum(album)
            emit(response)
        }
        postResponse.observe(this, Observer {
            val receivedAlbumsItem = it.body()
            val result = " "+"Album Title: ${receivedAlbumsItem?.title}"+"\n" +
                    " "+"Album id: ${receivedAlbumsItem?.id}"+"\n" +
                    " "+"User id: ${receivedAlbumsItem?.userId}"+"\n\n\n"
            tvAlbums.text = result
        })

    }
}