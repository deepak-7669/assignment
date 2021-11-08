package com.example.assignment.DInjection

import androidx.lifecycle.ViewModelProvider
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.example.assignment.BuildConfig
import com.example.assignment.Webservices.*
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
@Module
class NetworkModule {
    private val CONNECTIONTIMEOUT: Long = 100
    private val WRITETIMEOUT: Long = 100
    private val READTIMEOUT: Long = 100
    @Provides
    @Singleton
    fun provideGson(): Gson {
        val builder = GsonBuilder().setFieldNamingPolicy(
            FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES
        )
        return builder.setLenient().create()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Urls.DEVELOPMENT_BASE_URL)
        //    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(provideGson()))
           // .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun getApiCallInterface(retrofit: Retrofit): Apiservices {
        return retrofit.create(Apiservices::class.java)
    }
    @get:Singleton
    @get:Provides
    val requestHeader: OkHttpClient
        get() {
            val httpClient = OkHttpClient.Builder()
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            if (BuildConfig.DEBUG)
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            else
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
            httpClient.addInterceptor(httpLoggingInterceptor)
                .connectTimeout(CONNECTIONTIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITETIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READTIMEOUT, TimeUnit.SECONDS)
            return httpClient.build()
        }
    @Provides
    @Singleton
    fun getRepository(apiservices: Apiservices?): Repository {
        return apiservices?.let { Repository(it) }!!
    }

    @Provides
    @Singleton
    fun getViewModelFactory(repository: Repository?): ViewModelProvider.Factory {
        return repository?.let { Viewmodelfactorys(it) }!!
    }

    @Provides
    @Singleton
    fun getrepos(repository: Repository?):Any{
        return repository?.let { Accessrepo(repository) }!!
    }
}
