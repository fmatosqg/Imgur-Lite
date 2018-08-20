package com.example.fmatosqg.sample.imgurlight.domain.post

import com.example.fmatosqg.sample.imgurlight.ui.landing.PostCardViewModel
import io.reactivex.Single

interface IPostRepository {
    fun getPosts(keyword: String): Single<List<PostCardViewModel>>
}