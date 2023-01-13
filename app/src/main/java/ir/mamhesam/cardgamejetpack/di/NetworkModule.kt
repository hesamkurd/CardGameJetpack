package ir.mamhesam.cardgamejetpack.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.mamhesam.cardgamejetpack.data.local.CardGameDatabase
import ir.mamhesam.cardgamejetpack.data.remote.CardGameApi
import ir.mamhesam.cardgamejetpack.data.repository.RemoteDataSourceImpl
import ir.mamhesam.cardgamejetpack.domain.repository.RemoteDataSource
import ir.mamhesam.cardgamejetpack.util.Constants.BASE_URL
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@OptIn(ExperimentalSerializationApi::class)
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule
{
    
    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient{
        return OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.MINUTES)
            .connectTimeout(15, TimeUnit.MINUTES)
            .build()
    }
    
    @Provides
    @Singleton
    fun provideRetrofitInstance(okHttpClient : OkHttpClient): Retrofit {
        val contentType = "application/json".toMediaType()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(Json.asConverterFactory(contentType))
            .build()
    }
    
    @Provides
    @Singleton
    fun provideCardGameApi(retrofit : Retrofit): CardGameApi {
        return retrofit.create(CardGameApi::class.java)
    }
    
    @Provides
    @Singleton
    fun provideRemoteDataSource(
        cardGameApi : CardGameApi,
        cardGameDatabase : CardGameDatabase
    ): RemoteDataSource {
        return RemoteDataSourceImpl(
            cardGameApi = cardGameApi,
            cardGameDatabase = cardGameDatabase
        )
    }
}