package com.example.fmatosqg.sample.imgurlight.dagger

import android.app.Application
import com.example.fmatosqg.sample.imgurlight.BuildConfig
import com.example.fmatosqg.sample.imgurlight.domain.post.IPostRepository
import com.example.fmatosqg.sample.imgurlight.domain.post.PostMockRepository
import com.example.fmatosqg.sample.imgurlight.domain.post.PostRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class ActivityModule(private val application: Application) {

    @Provides
    @Singleton
    fun provideApplication() = application

    @Provides
    fun providesPostRepository(postMockRepository: PostMockRepository, postRepositoryImpl: PostRepositoryImpl): IPostRepository {

        return if (BuildConfig.hasMockedData) {
            postMockRepository
        } else {
            postRepositoryImpl
        }
    }
}