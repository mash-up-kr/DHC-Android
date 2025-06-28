package com.dhc.intro.category

import com.dhc.dhcandroid.repository.UserRepository
import com.dhc.presentation.mvi.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.dhc.intro.category.IntroCategoryContract.State
import com.dhc.intro.category.IntroCategoryContract.Event
import com.dhc.intro.category.IntroCategoryContract.SideEffect
import com.dhc.intro.category.model.CategoryItem

@HiltViewModel
class IntroCategoryViewModel @Inject constructor(
    private val repository: UserRepository,
) : BaseViewModel<State, Event, SideEffect>() {

    override fun createInitialState(): State {
        return State(
            categoryItems = listOf( // Todo : 서버에서 받아오기
                CategoryItem(0, "식음료", "https://cdn.dailyvet.co.kr/wp-content/uploads/2024/05/15231710/20240515ceva_experts5.jpg"),
                CategoryItem(1, "이동/교통", "https://cdn.dailyvet.co.kr/wp-content/uploads/2024/05/15231710/20240515ceva_experts5.jpg"),
                CategoryItem(2, "디지털/구독", "https://cdn.dailyvet.co.kr/wp-content/uploads/2024/05/15231710/20240515ceva_experts5.jpg"),
                CategoryItem(3, "쇼핑", "https://cdn.dailyvet.co.kr/wp-content/uploads/2024/05/15231710/20240515ceva_experts5.jpg"),
                CategoryItem(4, "취미/문화", "https://blog.malcang.com/wp-content/uploads/2024/03/1-1.png"),
                CategoryItem(5, "사교/모임", "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e6/Noto_Emoji_KitKat_263a.svg/1200px-Noto_Emoji_KitKat_263a.svg.png"),
            )
        )
    }

    override suspend fun handleEvent(event: Event) {
        when (event) {
            is Event.ClickNextButton -> {
                val selectedCategory = event.currentState.categoryItems.filterIndexed { index, _ ->
                    event.currentState.selectedIndexSet.contains(index)
                }
                repository.updateCategory(selectedCategory.map { it.toServerType() })
                repository.updateUserProfile()
                postSideEffect(SideEffect.NavigateToNextScreen)
            }
            is Event.ClickCategoryItem -> reduce {
                copy(
                    selectedIndexSet = if (selectedIndexSet.contains(event.selectedIndex)) {
                        selectedIndexSet - event.selectedIndex
                    } else {
                        selectedIndexSet + event.selectedIndex
                    }
                )
            }
        }
    }
}
