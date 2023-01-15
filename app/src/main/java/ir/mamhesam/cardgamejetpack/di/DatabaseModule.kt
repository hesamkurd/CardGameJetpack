package ir.mamhesam.cardgamejetpack.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ir.mamhesam.cardgamejetpack.data.local.CardGameDatabase
import ir.mamhesam.cardgamejetpack.data.repository.LocalDataSourceImpl
import ir.mamhesam.cardgamejetpack.domain.repository.LocalDataSource
import ir.mamhesam.cardgamejetpack.util.Constants.CARD_GAME_DATABASE
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule
{
    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context : Context
    ) : CardGameDatabase
    {
        return Room
            .databaseBuilder(
                context ,
                CardGameDatabase::class.java ,
                CARD_GAME_DATABASE
            )
            .build()
    }
    
    @Provides
    @Singleton
    fun provideLocalDataSource(
        cardGameDatabase : CardGameDatabase
    ): LocalDataSource {
        return LocalDataSourceImpl(
            cardGameDatabase = cardGameDatabase
        )
    }
}