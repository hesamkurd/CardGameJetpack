package ir.mamhesam.cardgamejetpack.domain.use_cases.get_all_heroes

import androidx.paging.PagingData
import ir.mamhesam.cardgamejetpack.data.repository.Repository
import ir.mamhesam.cardgamejetpack.domain.model.Hero
import kotlinx.coroutines.flow.Flow

class GetAllHeroesUseCase(
    private val repository : Repository
)
{
    operator fun invoke(): Flow<PagingData<Hero>> {
        return repository.getAllHeroes()
    }
}