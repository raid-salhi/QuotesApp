package com.example.quotesapp.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quotesapp.R
import com.example.quotesapp.model.QuoteItem

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
            ),
            navigationIcon = {
                if (!isMainScreen)
                    IconButton(onClick = { onAction }) {
                        Icon(imageVector = Icons.Rounded.ArrowBack, contentDescription = "Back", tint = Color.Black)
                    }

            }
        )

    }
@Composable
fun QuoteCard(quoteItem: QuoteItem,isMainScreen: Boolean=true,onAction: (QuoteItem) -> Unit={}) {
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
            if (isMainScreen){
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    IconButton(onClick = { onAction(quoteItem) }) {
                        androidx.compose.material.Icon(imageVector = Icons.Rounded.Favorite, contentDescription = "add")
                    }
                    Text(
                        text = "❞",
                        style = MaterialTheme.typography.h2,
                    )
                }
            }else{
                Text(
                    text = "❞",
                    style = MaterialTheme.typography.h2,
                    modifier = Modifier.align(Alignment.End)
                )
            }


        }
    }
}
