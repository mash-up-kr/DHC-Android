package com.dhc.intro.category

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dhc.designsystem.DhcTheme
import com.dhc.designsystem.DhcTypoTokens
import com.dhc.designsystem.button.DhcButton
import com.dhc.designsystem.button.model.DhcButtonSize
import com.dhc.designsystem.button.model.DhcButtonStyle
import com.dhc.designsystem.category.DhcCategoryItem
import com.dhc.designsystem.title.DhcTitle
import com.dhc.designsystem.title.DhcTitleState
import com.dhc.intro.R
import com.dhc.presentation.mvi.EventHandler

@Composable
fun IntroCategoryScreen(
    state: IntroCategoryContract.State,
    eventHandler: EventHandler<IntroCategoryContract.Event>,
    modifier: Modifier = Modifier,
) {
    val scrollState = rememberScrollState()
    Box(modifier = modifier) {
        Column(modifier = modifier.verticalScroll(scrollState)) {
            Spacer(modifier = Modifier.height(24.dp))
            DhcTitle(
                titleState = DhcTitleState(
                    title = stringResource(R.string.intro_category_title),
                    titleStyle = DhcTypoTokens.TitleH2,
                ),
                textAlign = TextAlign.Start,
                subTitleState = DhcTitleState(
                    title = stringResource(R.string.intro_category_sub_title),
                    titleStyle = DhcTypoTokens.Body3,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp, start = 20.dp, end = 20.dp),
            )
            Spacer(modifier = Modifier.height(23.dp))
            LazyVerticalGrid(
                columns = GridCells.Fixed(count = 2),
                contentPadding = PaddingValues(
                    top = 36.dp,
                    bottom = 38.dp,
                    start = 20.dp,
                    end = 20.dp,
                ),
                verticalArrangement = Arrangement.spacedBy(15.dp),
                horizontalArrangement = Arrangement.spacedBy(14.dp),
            ) {
                itemsIndexed(
                    items = state.categoryItems,
                    key = { _, item -> item.id },
                ) { categoryIndex, item ->
                    DhcCategoryItem(
                        name = item.name,
                        isChecked = state.selectedIndexSet.contains(categoryIndex),
                        imageUrl = item.imageUrl,
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1.6f)
                            .clickable {
                                eventHandler(
                                    IntroCategoryContract.Event.ClickCategoryItem(categoryIndex)
                                )
                            },
                    )
                }
            }
        }
        DhcButton(
            text = stringResource(R.string.personal_info_complete),
            buttonSize = DhcButtonSize.XLARGE,
            buttonStyle = DhcButtonStyle.Secondary(isEnabled = state.nextButtonEnabled),
            onClick = {
                if (state.nextButtonEnabled) {
                    eventHandler(IntroCategoryContract.Event.ClickNextButton)
                }
            },
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun IntroCategoryScreenPreview() {
    DhcTheme {
        IntroCategoryScreen(
            state = IntroCategoryContract.State(),
            eventHandler = {},
        )
    }
}
