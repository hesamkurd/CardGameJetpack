package ir.mamhesam.cardgamejetpack.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import ir.mamhesam.cardgamejetpack.data.local.CardGameDatabase
import ir.mamhesam.cardgamejetpack.data.paging_source.HeroRemoteMediator
import ir.mamhesam.cardgamejetpack.data.remote.CardGameApi
import ir.mamhesam.cardgamejetpack.domain.model.Hero
import ir.mamhesam.cardgamejetpack.domain.repository.RemoteDataSource
import ir.mamhesam.cardgamejetpack.util.Constants
import ir.mamhesam.cardgamejetpack.util.Constants.ITEMS_PER_PAGE
import kotlinx.coroutines.flow.Flow

@OptIn(ExperimentalPagingApi::class)
class RemoteDataSourceImpl(
    private val cardGameApi : CardGameApi,
    private val cardGameDatabase : CardGameDatabase
): RemoteDataSource
{
    private val heroDao = cardGameDatabase.heroDao()
    
    override fun getAllHeroes() : Flow<PagingData<Hero>>
    {
        val pagingSourceFactory = { heroDao.getAllHeroes() }
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            remoteMediator = HeroRemoteMediator(
                cardGameApi = cardGameApi,
                cardGameDatabase = cardGameDatabase
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }
    
    override fun searchHeroes() : Flow<PagingData<Hero>>
    {
        TODO("Not yet implemented")
    }
}