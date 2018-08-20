package com.example.fmatosqg.sample.imgurlight.domain.post.api

data class SearchResponse(val data: List<PostResponse>?)


data class PostResponse(val title: String?,
                        val datetime: Long?,
                        val images: List<PostImages>?)

data class PostImages(val link: String?,
                      val datetime: Long?)