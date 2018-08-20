package com.example.fmatosqg.sample.imgurlight.domain.post

import com.example.fmatosqg.sample.imgurlight.ui.landing.PostCardViewModel
import io.reactivex.Single
import javax.inject.Inject

class PostRepositoryImpl
@Inject constructor()
    : IPostRepository {
    override fun getPosts(keyword: String): Single<List<PostCardViewModel>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}