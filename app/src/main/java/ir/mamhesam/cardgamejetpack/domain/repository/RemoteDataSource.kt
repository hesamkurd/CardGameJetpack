package ir.mamhesam.cardgamejetpack.domain.repository

import androidx.paging.PagingData
import ir.mamhesam.cardgamejetpack.domain.model.Hero
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource
{
    fun getAllHeroes(): Flow<PagingData<Hero>>
    fun searchHeroes(query: String): Flow<PagingData<Hero>>
}