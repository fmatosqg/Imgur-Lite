package com.example.fmatosqg.sample.imgurlight.domain.post

import com.example.fmatosqg.sample.imgurlight.ui.landing.PostCardViewModel
import io.reactivex.Single
import javax.inject.Inject

class PostMockRepository
@Inject constructor()
    : IPostRepository {
    override fun getPosts(keyword:String ): Single<List<PostCardViewModel>> {

        val list = listOf(
                PostCardViewModel("https://78.media.tumblr.com/09218fd8e311a13d83f511251b4cbd3b/tumblr_pdk5bvvPY91qd9qa2o1_400.jpg", 10000, 0),
                PostCardViewModel("https://78.media.tumblr.com/09218fd8e311a13d83f511251b4cbd3b/tumblr_pdk5bvvPY91qd9qa2o1_400.jpg", 10100, 1),
                PostCardViewModel("https://78.media.tumblr.com/09218fd8e311a13d83f511251b4cbd3b/tumblr_pdk5bvvPY91qd9qa2o1_400.jpg", 10000, 2)
        )

        return Single.just(list)
    }
}