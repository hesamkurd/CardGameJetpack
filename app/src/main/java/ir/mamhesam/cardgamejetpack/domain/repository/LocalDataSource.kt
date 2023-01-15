package ir.mamhesam.cardgamejetpack.domain.repository

import ir.mamhesam.cardgamejetpack.domain.model.Hero

interface LocalDataSource
{
    suspend fun getSelectedHero(heroId: Int): Hero
}