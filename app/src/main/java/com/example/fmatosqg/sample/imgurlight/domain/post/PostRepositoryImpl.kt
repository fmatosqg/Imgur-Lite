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
    override fun getPosts(keyword: String, switchActive: Boolean): Single<List<PostCardViewModel>> {

        val authorizationHeader = "Client-ID " + BuildConfig.clientId


        val api =
                if (keyword.isNotBlank())
                    postApi.getPosts(authorizationHeader, keyword)
                else
                    postApi.getPosts(authorizationHeader)


        return api
                .map {
//                    Log.v("PostRepositoryImpl", "Look at data $it ")

                    convertToDomainModel(it, switchActive)

                }
    }

    private fun convertToDomainModel(searchResponse: SearchResponse, switchActive: Boolean): List<PostCardViewModel> {

        return if (searchResponse.data == null) {
            return listOf<PostCardViewModel>()
        } else {
            searchResponse
                    .data
                    .map { it.convertToDomainModel() }
                    .filter {

                        if (switchActive) {
                            it.isFilterTrue()
                        } else {
                            true
                        }
                    }
                    .sortedWith(compareByDescending { it.dateMs })
        }

    }
}

private fun PostResponse.convertToDomainModel(): PostCardViewModel {


    val firstUrl =
            images
                    ?.first()
                    ?.let {
                        it.link
                    }


    val imgCount = images?.size ?: 0

    val imgAdditionalCount =
            when (imgCount) {
                0 -> 0
                else -> imgCount - 1
            }

//    Log.v("Count check", "count = $imgCount // additional = $imgAdditionalCount")

    return PostCardViewModel(
            imgUrl = firstUrl ?: "",
            dateMs = (datetime ?: 0) * 1000L,
            imgAdditionalCount = imgAdditionalCount,

            points = points,
            score = score,
            topicId = topicId
    )

}
