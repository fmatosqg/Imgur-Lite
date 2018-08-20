package com.example.fmatosqg.sample.imgurlight.domain.post

import android.util.Log
import com.example.fmatosqg.sample.imgurlight.BuildConfig
import com.example.fmatosqg.sample.imgurlight.domain.post.api.PostApi
import com.example.fmatosqg.sample.imgurlight.domain.post.api.PostResponse
import com.example.fmatosqg.sample.imgurlight.domain.post.api.SearchResponse
import com.example.fmatosqg.sample.imgurlight.ui.landing.PostCardViewModel
import io.reactivex.Single
import javax.inject.Inject

class PostRepositoryImpl
@Inject constructor(private val postApi: PostApi)
    : IPostRepository {
    override fun getPosts(keyword: String): Single<List<PostCardViewModel>> {

        val authorizationHeader = "Client-ID " + BuildConfig.clientId

//        val sanitizedKeyword = if ( keyword.isNotBlank()) keyword else null

        val sanitizedKeyword = "for"
        return postApi
                .getPosts(authorizationHeader, sanitizedKeyword)
                .map {
                    Log.v("PostRepositoryImpl", "Look at data $it ")

                    convertToDomainModel(it)

                }
    }

    private fun convertToDomainModel(searchResponse: SearchResponse): List<PostCardViewModel> {

        if (searchResponse.data == null) {
            return listOf()
        }


        val list = mutableListOf<PostCardViewModel>()

        for (postResponse in searchResponse.data) {

            list.add(postResponse.convertToDomainModel())
        }

        return list

    }
}

private fun PostResponse.convertToDomainModel(): PostCardViewModel {

    val (firstUrl, dateTime) =
            images
                    ?.first()
                    ?.let {
                        it.link to it.datetime
                    }
                    ?: null to null

    return PostCardViewModel(
            imgUrl = firstUrl ?: "",
            dateMs = dateTime ?: 0,
            imgCount = images?.size ?: 0

    )

}
