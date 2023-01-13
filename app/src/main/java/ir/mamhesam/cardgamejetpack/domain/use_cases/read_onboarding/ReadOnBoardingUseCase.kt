package ir.mamhesam.cardgamejetpack.domain.use_cases.read_onboarding

import ir.mamhesam.cardgamejetpack.data.repository.Repository
import kotlinx.coroutines.flow.Flow

class ReadOnBoardingUseCase(
    private val repository : Repository
)
{
    operator fun invoke(): Flow<Boolean>{
        return repository.readOnBoardingState()
    }
}