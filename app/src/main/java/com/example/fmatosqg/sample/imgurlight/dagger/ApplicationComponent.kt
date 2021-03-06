package com.example.fmatosqg.sample.imgurlight.dagger

import com.example.fmatosqg.sample.imgurlight.ui.landing.LandingActivity
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = arrayOf(ActivityModule::class, NetworkModule::class))
interface ApplicationComponent {
    fun inject(mainActivity: LandingActivity)
}