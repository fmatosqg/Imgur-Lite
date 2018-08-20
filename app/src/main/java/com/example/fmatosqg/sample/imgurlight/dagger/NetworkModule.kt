package com.example.fmatosqg.sample.imgurlight.dagger

import android.app.Application
import android.util.Log
import com.example.fmatosqg.sample.imgurlight.domain.post.api.PostApi
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class NetworkModule() {


    companion object {
        const val DOMAIN_NAME = "https://api.imgur.com"
    }

    @Provides
    @Singleton
    internal fun provideHttpCache(application: Application): Cache {
        val cacheSize: Long = 10 * 1024 * 1024
        val cache = Cache(application.cacheDir, cacheSize)
        Log.d("NetModule", "App cache dir is " + application.cacheDir)
        return cache
    }

    @Provides
    @Singleton
    internal fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        return gsonBuilder.create()
    }

    @Provides
    @Singleton
    internal fun provideOkhttpClient(cache: Cache): OkHttpClient {
        val client = OkHttpClient.Builder()
        client.cache(cache)
        return client.build()
    }


    @Provides
    @Singleton
    fun providesLeadsApi(httpClient: OkHttpClient,
                         gson: Gson): PostApi {

        val retrofit = buildRetrofit(httpClient, gson, DOMAIN_NAME)

        return retrofit.create(PostApi::class.java)
    }


    private fun buildRetrofit(httpClient: OkHttpClient, gson: Gson,
                              serverDomainName: String): Retrofit {

        return Retrofit.Builder()
                .client(httpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(serverDomainName)
                .build()
    }
}