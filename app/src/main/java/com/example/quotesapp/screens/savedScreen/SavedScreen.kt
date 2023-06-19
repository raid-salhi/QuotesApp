package com.example.quotesapp.screens.savedScreen


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Button
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.quotesapp.model.QuoteItem
import com.example.quotesapp.widgets.CustomtopBar
import com.example.quotesapp.widgets.NothingToShow
import com.example.quotesapp.widgets.QuoteCard
import com.example.quotesapp.widgets.SwipeableBackground
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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



@OptIn(DelicateCoroutinesApi::class)
@Composable
fun Content(listQuoteItem: List<QuoteItem>,savedScreenViewModel: SavedScreenViewModel) {
    var isVisible by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(key1 = null){
        delay(100)
        isVisible=!isVisible
    }

    Column(verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Swipe right to Delete ",
            style = MaterialTheme.typography.caption,
            fontStyle = FontStyle.Italic,
            modifier = Modifier.padding(10.dp)
        )
        LazyColumn{

            itemsIndexed(
                listQuoteItem,
                key = {index, item ->
                item.hashCode()
            }
            ) { index, item ->
                SwipeableQuoteCard(quoteItem = item, savedScreenViewModel = savedScreenViewModel, index = index, visible = isVisible)
            }

        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SwipeableQuoteCard(quoteItem: QuoteItem,savedScreenViewModel: SavedScreenViewModel,index:Int,visible :Boolean) {

    val state = androidx.compose.material3.rememberDismissState(
        initialValue = androidx.compose.material3.DismissValue.Default,
        confirmValueChange = {
            if (it==androidx.compose.material3.DismissValue.DismissedToEnd)
                savedScreenViewModel.deleteQuote(quoteItem = quoteItem)
            true
        }

    )
    AnimatedVisibility(
        visible = visible,
        enter = slideInHorizontally(animationSpec = tween(durationMillis =1000, delayMillis = index*300)) { -it }) {
        SwipeToDismiss(
            state = state,
            background = {SwipeableBackground()},
            directions = setOf(DismissDirection.StartToEnd),
            dismissContent = { QuoteCard(quoteItem = quoteItem,isMainScreen = false)}
        )
    }

}

