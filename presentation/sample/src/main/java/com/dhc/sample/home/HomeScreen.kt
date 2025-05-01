package com.dhc.dhcandroid.home

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dhc.presentation.mvi.EventHandler

@Composable
fun HomeRoute(
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect { sideEffect ->
            when(sideEffect) {
                is HomeContract.SideEffect.ShowToast -> {
                    Toast.makeText(context, sideEffect.msg, Toast.LENGTH_SHORT).show()
                }
            }

        }
    }
    HomeScreen(
        state = state,
        eventHandler = viewModel::sendEvent,
    )
}

@Composable
fun HomeScreen(
    state: HomeContract.State,
    eventHandler: com.dhc.presentation.mvi.EventHandler<HomeContract.Event>,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(text = "Current Value : ${state.number}")

        Button(onClick = { eventHandler(HomeContract.Event.ClickAddButton) }) {
            Text(text = "Add")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { eventHandler(HomeContract.Event.ClickMinusButton) }) {
            Text(text = "Minus")
        }
    }

}
