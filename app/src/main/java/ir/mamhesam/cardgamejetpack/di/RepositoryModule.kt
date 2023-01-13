package ir.mamhesam.cardgamejetpack.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ir.mamhesam.cardgamejetpack.data.repository.DataStoreOperationsImpl
import ir.mamhesam.cardgamejetpack.data.repository.Repository
import ir.mamhesam.cardgamejetpack.domain.repository.DataStoreOperations
import ir.mamhesam.cardgamejetpack.domain.use_cases.UseCases
import ir.mamhesam.cardgamejetpack.domain.use_cases.get_all_heroes.GetAllHeroesUseCase
import ir.mamhesam.cardgamejetpack.domain.use_cases.read_onboarding.ReadOnBoardingUseCase
import ir.mamhesam.cardgamejetpack.domain.use_cases.save_onborading.SaveOnBoardingUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule
{
    @Provides
    @Singleton
    fun provideDataStoreOperation(@ApplicationContext context : Context) : DataStoreOperations
    {
        return DataStoreOperationsImpl(context = context)
    }
    
    @Provides
    @Singleton
    fun provideUseCases(repository : Repository): UseCases{
        return UseCases(
            saveOnBoardingUseCase = SaveOnBoardingUseCase(repository),
            readOnBoardingUseCase = ReadOnBoardingUseCase(repository),
            getAllHeroesUseCase = GetAllHeroesUseCase(repository)
        )
    }
}