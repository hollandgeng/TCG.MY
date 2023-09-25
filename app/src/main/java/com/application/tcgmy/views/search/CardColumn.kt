package com.application.tcgmy.views.search

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.application.tcgmy.R
import com.application.tcgmy.domain.SimpleCard
import com.valentinilk.shimmer.ShimmerBounds
import com.valentinilk.shimmer.defaultShimmerTheme
import com.valentinilk.shimmer.rememberShimmer
import com.valentinilk.shimmer.shimmer

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun CardLazyColumn(
    sections: Map<String, List<SimpleCard>>,
    modifier: Modifier = Modifier
) {
    val collapsedState = remember(sections) {
        sections.map {
            true
        }.toMutableStateList()
    }

    LazyColumn(modifier) {
        sections.toList().forEachIndexed { i, item ->
            val collapsed = collapsedState[i]
            stickyHeader {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            bottom = 2.dp
                        ),
                    shape = RoundedCornerShape(10.dp),
                    onClick = {
                        collapsedState[i] = !collapsed
                    }
                ) {
                    Text(
                        text = item.first,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.primaryContainer)
                            .padding(
                                vertical = 16.dp,
                                horizontal = 10.dp
                            )
                    )
                }
            }

            items(item.second) {
                AnimatedVisibility(visible = !collapsed) {
                    CardColumn(it)
                }
            }
        }
    }
}

@Composable
fun CardColumn(
    card: SimpleCard = SimpleCard()
) {
    Card(
        modifier = Modifier
            .height(100.dp)
            .padding(10.dp),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(15.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            AsyncImage(
                model = card.image,
                contentDescription = "Image",
                placeholder = painterResource(id = R.drawable.playing_cards),
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(10.dp)
            )

            Column(
                modifier = Modifier.weight(2f),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top
            ) {
                Text(text = card.code)
                Spacer(modifier = Modifier.height(5.dp))
                Text(text = card.name)
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                contentAlignment = Alignment.BottomEnd
            ) {
                Row(
                    modifier = Modifier.padding(10.dp)
                ) {
                    Text(text = card.price.toString())
                    Text(text = card.currency.grapheme)
                }
            }

        }
    }
}

@Composable
fun LoadingCard() {
    val shimmerInstance = rememberShimmer(
        shimmerBounds = ShimmerBounds.View,
        theme = createCustomTheme()
    )

    Card(
        modifier = Modifier
            .height(100.dp)
            .padding(10.dp),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(15.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(80.dp)
                    .padding(10.dp)
                    .shimmer(customShimmer = shimmerInstance)
                    .background(Color(0xFFB8B5B5))
            )

            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top
            ) {
                Box(
                    modifier = Modifier
                        .width(50.dp)
                        .height(20.dp)
                        .shimmer(customShimmer = shimmerInstance)
                        .background(Color(0xFFB8B5B5))
                )
                Spacer(modifier = Modifier.height(5.dp))
                Box(
                    modifier = Modifier
                        .width(150.dp)
                        .height(20.dp)
                        .shimmer(customShimmer = shimmerInstance)
                        .background(Color(0xFFB8B5B5))
                )
            }
        }
    }
}

private fun createCustomTheme() = defaultShimmerTheme.copy(
    animationSpec = infiniteRepeatable(
        animation = tween(
            800,
            easing = LinearEasing,
            delayMillis = 500,
        ),
        repeatMode = RepeatMode.Restart,
    ),
    blendMode = BlendMode.DstIn,
    rotation = 15.0f,
    shaderColors = listOf(
        Color.Unspecified.copy(alpha = 0.25f),
        Color.Unspecified.copy(alpha = 1.00f),
        Color.Unspecified.copy(alpha = 0.25f),
    ),
    shaderColorStops = listOf(
        0.0f,
        0.5f,
        1.0f,
    ),
    shimmerWidth = 400.dp,
)

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
//    CardColumn(
//        card = SimpleCard(
//            code = "test",
//            name = "test",
//            rarity = "test",
//            image = "",
//            price = 0,
//            currency = "yen"
//        )
//    )
    LoadingCard()
}