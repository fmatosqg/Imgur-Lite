package com.example.fmatosqg.sample.imgurlight.domain.post

import android.util.Log
import com.example.fmatosqg.sample.imgurlight.BuildConfig
import com.example.fmatosqg.sample.imgurlight.domain.post.api.PostApi
import com.example.fmatosqg.sample.imgurlight.ui.landing.PostCardViewModel
import io.reactivex.Single
import javax.inject.Inject

class PostRepositoryImpl
@Inject constructor(private val postApi: PostApi)
    : IPostRepository {
    override fun getPosts(keyword: String): Single<List<PostCardViewModel>> {

        val authorizationHeader = "Client-ID " + BuildConfig.clientId

        return postApi
                .getPosts(authorizationHeader)
                .map {
                    Log.i("PostRepositoryImpl", "Look at data $it ")

                    listOf<PostCardViewModel>()

                }
    }
}