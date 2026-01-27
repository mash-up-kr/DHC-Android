package com.dhc.intro.category

import com.dhc.intro.category.model.CategoryItem
import com.dhc.presentation.mvi.UiEvent
import com.dhc.presentation.mvi.UiSideEffect
import com.dhc.presentation.mvi.UiState

class IntroCategoryContract {

    data class State(
        val categoryItems: List<CategoryItem> = emptyList(),
        val selectedIndexSet: Set<Int> = emptySet(),
        val loadState: LoadState = LoadState.Loading,
    ) : UiState {
        val selectedCategoryItems get() = categoryItems.filterIndexed { index, _ -> selectedIndexSet.contains(index) }
        val nextButtonEnabled: Boolean = selectedIndexSet.size > 2
    }

    enum class LoadState {
        Loading,
        Success,
        Error,
    }

    sealed interface Event : UiEvent {
        data class ClickNextButton(val currentState: State) : Event
        data class ClickCategoryItem(val selectedIndex: Int) : Event
        data object ClickRetryButton : Event
    }

    sealed interface SideEffect : UiSideEffect {
        data object NavigateToNextScreen : SideEffect
    }
}
