package ir.mamhesam.cardgamejetpack.domain.use_cases.get_selected_hero

import ir.mamhesam.cardgamejetpack.data.repository.Repository
import ir.mamhesam.cardgamejetpack.domain.model.Hero

class GetSelectedHeroUseCase(
    private val repository : Repository
)
{
    suspend operator fun invoke(heroId: Int): Hero {
        return repository.getSelectedHero(heroId = heroId)
    }
}