package pro.sparrow_team.qq.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.newSingleThreadContext
import okhttp3.CacheControl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import pro.sparrow_team.qq.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Provides
    @Singleton
    fun networkInterceptor() : HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        return logging
    }


    @Provides
    @Singleton
    fun provideOKHttpClient() = OkHttpClient.Builder()
        .connectTimeout(Constants.TIMEOUT,TimeUnit.SECONDS)
        .readTimeout(Constants.TIMEOUT,TimeUnit.SECONDS)
        .writeTimeout(Constants.TIMEOUT,TimeUnit.SECONDS)
        .addNetworkInterceptor {chain->
            val request = chain.request().newBuilder()
                .addHeader("Cache-control", "no-cache")
                .addHeader("Package-Name","pro.sparrow_team.qq")
                .cacheControl(CacheControl.FORCE_NETWORK)
            chain.proceed(request.build())
        }
        .addNetworkInterceptor(networkInterceptor())
        .cache(null)
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(Constants.BASE_URL)
        .client(okHttpClient)
        .build()

}