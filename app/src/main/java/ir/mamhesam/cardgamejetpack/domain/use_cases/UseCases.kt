package ir.mamhesam.cardgamejetpack.domain.use_cases

import ir.mamhesam.cardgamejetpack.domain.use_cases.get_all_heroes.GetAllHeroesUseCase
import ir.mamhesam.cardgamejetpack.domain.use_cases.read_onboarding.ReadOnBoardingUseCase
import ir.mamhesam.cardgamejetpack.domain.use_cases.save_onborading.SaveOnBoardingUseCase
import ir.mamhesam.cardgamejetpack.domain.use_cases.search_heroes.SearchHeroesUseCase

data class UseCases(
    val saveOnBoardingUseCase : SaveOnBoardingUseCase,
    val readOnBoardingUseCase : ReadOnBoardingUseCase,
    val getAllHeroesUseCase : GetAllHeroesUseCase,
    val searchHeroesUseCase : SearchHeroesUseCase
)
