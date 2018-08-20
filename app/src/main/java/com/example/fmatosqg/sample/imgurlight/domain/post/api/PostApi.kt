package com.example.fmatosqg.sample.imgurlight.domain.post.api

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query


interface PostApi {

    // only first page
    @GET("/3/gallery/search/time/week/1")
    fun getPosts(
            @Header("Authorization") authorizationHeader: String,
            @Query("q_any") keyword: String?
    ): Single<SearchResponse>


}
