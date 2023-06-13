package com.example.quotesapp.screens.mainSceen

import android.widget.Toast
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
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.quotesapp.R
import com.example.quotesapp.model.Quote
import com.example.quotesapp.model.QuoteItem
import com.example.quotesapp.navigaion.ScreensRoute
import com.example.quotesapp.screens.savedScreen.SavedScreenViewModel
import com.example.quotesapp.widgets.CustomtopBar
import com.example.quotesapp.widgets.QuoteCard

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
            FloatingActionButton(onClick = { navController.navigate(ScreensRoute.SavedScreen.name) }, shape = RoundedCornerShape(16.dp), modifier = Modifier.padding(bottom = 20.dp, end = 15.dp)) {
                Icon(imageVector = Icons.Rounded.Favorite, contentDescription = null)
            }
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

@Composable
fun ShowLottieAnimation() {
    val composition = rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.lottie1))
    LottieAnimation(composition = composition.value, iterations = LottieConstants.IterateForever)
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

