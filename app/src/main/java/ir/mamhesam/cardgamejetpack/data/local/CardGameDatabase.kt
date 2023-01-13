package ir.mamhesam.cardgamejetpack.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ir.mamhesam.cardgamejetpack.data.local.dao.HeroDao
import ir.mamhesam.cardgamejetpack.data.local.dao.HeroRemoteKeysDao
import ir.mamhesam.cardgamejetpack.domain.model.Hero
import ir.mamhesam.cardgamejetpack.domain.model.HeroRemoteKeys


@Database(entities = [Hero::class, HeroRemoteKeys::class] , version = 1 , exportSchema = false)
@TypeConverters(DatabaseConverter::class)
abstract class CardGameDatabase : RoomDatabase()
{
    abstract fun heroDao(): HeroDao
    abstract fun heroRemoteKeysDao(): HeroRemoteKeysDao
}