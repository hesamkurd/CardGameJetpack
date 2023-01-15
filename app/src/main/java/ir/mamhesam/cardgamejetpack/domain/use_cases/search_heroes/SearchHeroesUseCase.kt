package ir.mamhesam.cardgamejetpack.domain.use_cases.search_heroes

import androidx.paging.PagingData
import ir.mamhesam.cardgamejetpack.data.repository.Repository
import ir.mamhesam.cardgamejetpack.domain.model.Hero
import kotlinx.coroutines.flow.Flow

class SearchHeroesUseCase(
    private val repository : Repository
)
{
    operator fun invoke(query: String): Flow<PagingData<Hero>> {
        return repository.searchHeroes(query = query)
    }
}