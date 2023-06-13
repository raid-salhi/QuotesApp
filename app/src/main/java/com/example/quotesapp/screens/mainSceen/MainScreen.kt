package com.example.quotesapp.screens.mainSceen


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.quotesapp.R
import com.example.quotesapp.model.Quote
import com.example.quotesapp.navigaion.ScreensRoute
import com.example.quotesapp.screens.savedScreen.SavedScreenViewModel
import com.example.quotesapp.widgets.CustomtopBar
import com.example.quotesapp.widgets.QuoteCard
import com.example.quotesapp.widgets.ShowLottieAnimation

@Composable
fun MainScreen(
    navController: NavController,
    mainScreenViewmodel: MainScreenViewmodel = hiltViewModel(),
    savedScreenViewModel: SavedScreenViewModel= hiltViewModel()
){
    Scaffold(
        topBar = {
            CustomtopBar(title = "Quotty")
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text(text = "Saved Quotes")},
                onClick = { navController.navigate(ScreensRoute.SavedScreen.name)  },
                icon={
                        Icon(imageVector = ImageVector.vectorResource(id = R.drawable.saved), contentDescription = null, modifier = Modifier.size(25.dp))
                },
                modifier = Modifier.padding(bottom = 20.dp, end = 15.dp)
            )
        },
        floatingActionButtonPosition = FabPosition.End
    ) {
        MainContent(mainScreenViewmodel,it,savedScreenViewModel)
    }
}



@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainContent(mainScreenViewmodel: MainScreenViewmodel,it: PaddingValues,savedScreenViewModel: SavedScreenViewModel) {
    val list =mainScreenViewmodel.data.value
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
            .padding(it)
    ) {
        Spacer(modifier = Modifier.height(120.dp))
        Text(
            text = "Swipe right to navigate to the second quote",
            style = MaterialTheme.typography.body1,
            fontStyle = FontStyle.Italic,
            modifier = Modifier
        )
        Spacer(modifier = Modifier.height(120.dp))

        if (list.isEmpty()) ShowLottieAnimation()
        else QuoteSwipeable(list,savedScreenViewModel)

    }
}



@OptIn(ExperimentalFoundationApi::class)
@Composable
fun QuoteSwipeable(list: Quote,savedScreenViewModel: SavedScreenViewModel) {
    val pagerState= rememberPagerState()
    HorizontalPager(
        pageCount = list.size,
        state = pagerState,
        key = {list[it].c}
    ) { index ->
        QuoteCard(quoteItem = list[index], isMainScreen = true){
            savedScreenViewModel.addQuote(it)

        }
    }
}

