package ir.mamhesam.cardgamejetpack.presentation.screens.details

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.mamhesam.cardgamejetpack.domain.model.Hero
import ir.mamhesam.cardgamejetpack.domain.use_cases.UseCases
import ir.mamhesam.cardgamejetpack.util.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val useCases : UseCases,
    savedStateHandle : SavedStateHandle
): ViewModel()
{
    private val _selectedHero: MutableStateFlow<Hero?> = MutableStateFlow(null)
    val selectedHero: StateFlow<Hero?> = _selectedHero
    
    init
    {
        viewModelScope.launch(Dispatchers.IO){
            val heroId = savedStateHandle.get<Int>(Constants.DETAILS_ARGUMENT_KEY)
            _selectedHero.value = heroId?.let {
                useCases.getSelectedHeroUseCase(heroId = heroId)
            }
        }
    }
}