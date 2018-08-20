package com.example.fmatosqg.sample.imgurlight.domain.post.api

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header


interface PostApi {

    @GET("/api/posts/")

    fun getPosts(
            @Header("Authorization") authorizationHeader: String
    ): Single<List<PostResponse>>
}

data class PostResponse(val imgUrl: String?)