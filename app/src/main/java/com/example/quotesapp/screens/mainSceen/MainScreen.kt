package com.example.quotesapp.screens.mainSceen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFontFamilyResolver
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.quotesapp.R
import com.example.quotesapp.model.QuoteItem

@Composable
fun MainScreen(mainScreenViewmodel: MainScreenViewmodel = hiltViewModel()){
    Scaffold(
//        topBar = {
//            CustomtopBar()
//        }
    ) {
        MainContent(mainScreenViewmodel,it)
    }
}

@Composable
fun CustomtopBar() {
    TODO("Not yet implemented")
}

@Composable
fun MainContent(mainScreenViewmodel: MainScreenViewmodel,it: PaddingValues) {
    val list =mainScreenViewmodel.data.value
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(it)
    ) {
        if (list.isNotEmpty())
            QuoteCard(list[0])

    }
}

@Composable
fun QuoteCard(quoteItem: QuoteItem) {
    Surface(
        shape = RoundedCornerShape(20.dp),
        color = MaterialTheme.colors.secondary,
        elevation = 10.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        Column(horizontalAlignment = Alignment.Start, modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)) {
            Text(
                text = "❝",
                style = MaterialTheme.typography.h2,
                modifier = Modifier.padding(bottom = 15.dp)
            )
            Text(
                text = quoteItem.q,
                style = MaterialTheme.typography.h5,
                fontFamily = FontFamily(listOf(Font(R.font.unna))),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 10.dp)
            )
            Text(
                text = "-"+quoteItem.a,
                style = MaterialTheme.typography.caption,
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(end = 15.dp, bottom = 15.dp)
            )
            Text(
                text = "❞",
                style = MaterialTheme.typography.h2,
                modifier = Modifier.align(Alignment.End)
            )
        }
    }
}
