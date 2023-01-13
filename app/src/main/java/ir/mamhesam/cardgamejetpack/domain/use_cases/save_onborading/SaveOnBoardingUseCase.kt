package ir.mamhesam.cardgamejetpack.domain.use_cases.save_onborading

import ir.mamhesam.cardgamejetpack.data.repository.Repository

class SaveOnBoardingUseCase(
    private val repository : Repository
)
{
    suspend operator fun invoke(completed: Boolean){
        repository.saveOnBoardingState(completed = completed)
    }
}