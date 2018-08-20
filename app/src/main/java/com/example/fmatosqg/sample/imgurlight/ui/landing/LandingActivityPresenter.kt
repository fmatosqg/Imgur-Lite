package com.example.fmatosqg.sample.imgurlight.ui.landing

import com.example.fmatosqg.sample.imgurlight.domain.post.IPostRepository
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LandingActivityPresenter
@Inject constructor(private val postRepository: IPostRepository) {

    fun getData(keyword: String? = null): Single<List<PostCardViewModel>> {

        return postRepository.getPosts(keyword ?: "")
    }
}

