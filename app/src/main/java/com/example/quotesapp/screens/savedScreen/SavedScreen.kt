package com.example.quotesapp.screens.savedScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.quotesapp.R
import com.example.quotesapp.model.QuoteItem
import com.example.quotesapp.widgets.CustomtopBar
import com.example.quotesapp.widgets.QuoteCard

@Composable
fun SavedScreen(navController: NavController, savedScreenViewModel: SavedScreenViewModel = hiltViewModel()){
    Scaffold(
        topBar = {
            CustomtopBar(title = "Saved Quotes", isMainScreen = false){
                navController.popBackStack()
            }
        }
    ) {
        SavedScreenContent(savedScreenViewModel,it)
    }
}

@Composable
fun SavedScreenContent(savedScreenViewModel: SavedScreenViewModel, it: PaddingValues) {
    val quotes = savedScreenViewModel.data.collectAsState()
    val listQuoteItem = quotes.value
    Surface(modifier = Modifier
        .fillMaxSize()
        .padding(it)) {
        if (listQuoteItem.isEmpty()) NothingToShow()
        else Content(listQuoteItem = listQuoteItem,savedScreenViewModel)
    }
}

@Composable
fun NothingToShow() {
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
        Image(imageVector = ImageVector.vectorResource(id = R.drawable.empty), contentDescription = "empty")
    }
}

@Composable
fun Content(listQuoteItem: List<QuoteItem>,savedScreenViewModel: SavedScreenViewModel) {
    Column(verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Swipe right to Delete ",
            style = MaterialTheme.typography.caption,
            fontStyle = FontStyle.Italic,
            modifier = Modifier.padding(10.dp)
        )
        LazyColumn{
            items(
                listQuoteItem,
                key = {
                    it.hashCode()
                }
            ){
                SwipeableQuoteCard(it,savedScreenViewModel)
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SwipeableQuoteCard(quoteItem: QuoteItem,savedScreenViewModel: SavedScreenViewModel) {
    val state = androidx.compose.material3.rememberDismissState(
        initialValue = androidx.compose.material3.DismissValue.Default,
        confirmValueChange = {
            if (it==androidx.compose.material3.DismissValue.DismissedToEnd)
                savedScreenViewModel.deleteQuote(quoteItem = quoteItem)
            true
        }

    )
    SwipeToDismiss(
        state = state,
        background = {SwipeableBackground()},
        directions = setOf(DismissDirection.StartToEnd),
        dismissContent = { QuoteCard(quoteItem = quoteItem,isMainScreen = false)}
    )
}
@Composable
fun SwipeableBackground(){
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(20.dp)
        .clip(shape = RoundedCornerShape(20.dp))
        .background(Color.Red.copy(alpha = 0.5f)),
        contentAlignment = Alignment.CenterStart
    ){
        Icon(painter = painterResource(id = R.drawable.delete), contentDescription = "delete")
    }
}
