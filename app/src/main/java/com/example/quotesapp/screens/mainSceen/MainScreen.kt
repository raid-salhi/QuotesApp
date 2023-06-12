package com.example.quotesapp.screens.mainSceen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.quotesapp.R
import com.example.quotesapp.model.Quote
import com.example.quotesapp.model.QuoteItem

@Composable
fun MainScreen(mainScreenViewmodel: MainScreenViewmodel = hiltViewModel()){
    Scaffold(
        topBar = {
            CustomtopBar(title = "Quizzy")
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {  }, shape = RoundedCornerShape(16.dp), modifier = Modifier.padding(bottom = 20.dp, end = 15.dp)) {
                Icon(imageVector = Icons.Rounded.Favorite, contentDescription = null)
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) {
        MainContent(mainScreenViewmodel,it)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomtopBar(
    isMainScreen : Boolean = true,
    title : String,
    onAction : () -> Unit ={}
) {
    CenterAlignedTopAppBar(
        title = {
                Text(
                    text = title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colors.secondary,
            navigationIconContentColor = Color.White,
            titleContentColor = Color.White,
            actionIconContentColor = Color.White
        )
    )

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainContent(mainScreenViewmodel: MainScreenViewmodel,it: PaddingValues) {
    val list =mainScreenViewmodel.data.value
    val pagerState= rememberPagerState()
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

        if (list.isNotEmpty()){
            QuoteSwipeable(list)
        }


    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun QuoteSwipeable(list: Quote) {
    val pagerState= rememberPagerState()
    HorizontalPager(
        pageCount = list.size,
        state = pagerState,
        key = {list[it].c}
    ) { index ->
        QuoteCard(quoteItem = list[index])
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
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                IconButton(onClick = {  }) {
                    Icon(imageVector = Icons.Rounded.AddCircle, contentDescription = "add")
                }
                Text(
                    text = "❞",
                    style = MaterialTheme.typography.h2,
                )
            }

        }
    }
}
