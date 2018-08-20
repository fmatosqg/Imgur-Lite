package com.example.fmatosqg.sample.imgurlight.common

import android.app.Application
import com.example.fmatosqg.sample.imgurlight.dagger.ActivityModule
import com.example.fmatosqg.sample.imgurlight.dagger.ApplicationComponent
import com.example.fmatosqg.sample.imgurlight.dagger.DaggerApplicationComponent

class ImgurApplication : Application() {

    companion object {
        //  platformStatic allow access it from java code
        @JvmStatic
        lateinit var graph: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()

        graph = DaggerApplicationComponent
                .builder()
                .activityModule(ActivityModule(this))
                .build()

    }
}

