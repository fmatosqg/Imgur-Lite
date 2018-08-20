package com.example.fmatosqg.sample.imgurlight.dagger

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class ActivityModule(private val application: Application) {

    @Provides
    @Singleton
    fun provideApplication() = application

}