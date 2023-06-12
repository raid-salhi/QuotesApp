package com.example.quotesapp.screens.mainSceen

import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun MainScreen(mainScreenViewmodel: MainScreenViewmodel = hiltViewModel()){
    Scaffold() {
        MainContent(mainScreenViewmodel,it)
    }
}

@Composable
fun MainContent(mainScreenViewmodel: MainScreenViewmodel,it:PaddingValues) {
    val list =mainScreenViewmodel.data.value
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(it)
    ) {
        if (list.isNotEmpty())
            Text(text = list[0].q)

    }
}
