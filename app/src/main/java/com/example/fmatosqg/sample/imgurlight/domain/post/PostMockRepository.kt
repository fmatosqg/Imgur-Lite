package com.example.fmatosqg.sample.imgurlight.domain.post

import com.example.fmatosqg.sample.imgurlight.ui.landing.PostCardViewModel
import io.reactivex.Single
import javax.inject.Inject

class PostMockRepository
@Inject constructor()
    : IPostRepository {
    override fun getPosts(keyword:String ): Single<List<PostCardViewModel>> {

        val list = listOf(
                PostCardViewModel("123", 10000, 0),
                PostCardViewModel("123", 10100, 1),
                PostCardViewModel("123", 10000, 2)
        )

        return Single.just(list)
    }
}