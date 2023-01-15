package ir.mamhesam.cardgamejetpack.data.repository

import ir.mamhesam.cardgamejetpack.data.local.CardGameDatabase
import ir.mamhesam.cardgamejetpack.domain.model.Hero
import ir.mamhesam.cardgamejetpack.domain.repository.LocalDataSource

class LocalDataSourceImpl(
    cardGameDatabase : CardGameDatabase
): LocalDataSource
{
    private val heroDao = cardGameDatabase.heroDao()
    
    override suspend fun getSelectedHero(heroId : Int) : Hero
    {
        return heroDao.getSelectedHero(heroId = heroId)
    }
}