package com.ea.data.module

import android.os.Handler
import android.os.Looper
import com.apollographql.apollo.ApolloClient
import com.ea.data.BuildConfig
import com.ea.data.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideApolloClient(okHttpClient: OkHttpClient): ApolloClient{
        return ApolloClient.builder()
            .serverUrl("https://countries.trevorblades.com/")
            .okHttpClient(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(interceptor: HttpLoggingInterceptor) = OkHttpClient.Builder()
        .connectTimeout(Constants.TIME_OUT, TimeUnit.SECONDS)
        .readTimeout(Constants.TIME_OUT, TimeUnit.SECONDS)
        .writeTimeout(Constants.TIME_OUT, TimeUnit.SECONDS)
        .addInterceptor(interceptor)
        .build()

    @Singleton
    @Provides
    fun provideHttpLogInterceptor() = if(BuildConfig.DEBUG)
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    else
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.NONE
        }

//    @Provides
//    fun provideMainHandler(): Handler {
//        return Handler(Looper.getMainLooper())
//    }

}