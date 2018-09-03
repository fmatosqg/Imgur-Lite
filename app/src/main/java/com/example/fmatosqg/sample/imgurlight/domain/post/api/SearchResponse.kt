package com.example.fmatosqg.sample.imgurlight.domain.post.api

data class SearchResponse(val data: List<PostResponse>?)


data class PostResponse(val title: String?,
                        val datetime: Long?,
                        val images: List<PostImages>?,

                        val points: Long,
                        val score: Long,
                        val topicId: Long,

                        val imagesCount: Int?,
                        val isAlbum: Boolean?,
                        val link: String?)

data class PostImages(val link: String?,
                      val datetime: Long?)