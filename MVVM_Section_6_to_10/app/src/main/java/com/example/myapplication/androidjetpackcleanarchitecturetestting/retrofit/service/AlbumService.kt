package com.example.myapplication.androidjetpackcleanarchitecturetestting.retrofit.service

import com.example.myapplication.androidjetpackcleanarchitecturetestting.retrofit.model.AlbumItem
import com.example.myapplication.androidjetpackcleanarchitecturetestting.retrofit.model.Albums
import retrofit2.Response
import retrofit2.http.*

interface AlbumService {
    @GET("/albums")
    suspend fun getAlbums (): Response<Albums>

    @GET("/albums")
    suspend fun getAlbumsByUserId (@Query("userId") userId: Int): Response<Albums>

    @GET("/albums/{id}")
    suspend fun getAlbum (@Path("id") albumId: Int): Response<AlbumItem>

    @POST("/albums")
    suspend fun uploadAlbum(@Body album: AlbumItem): Response<AlbumItem>

}