package com.application.tcgmy.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.application.tcgmy.R
import com.application.tcgmy.data.GameList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TcgDrawer(onMenuIconClicked : () -> Unit = {})
{
    ModalDrawerSheet(Modifier.padding(end = 20.dp)) {

        TcgDrawer_Header(onMenuIconClicked)

        LazyColumn {
            items(items= GameList, key = { game->game.id})
            {
                NavigationDrawerItem(label = { Text(it.title) }, selected = false, onClick = { },
                    icon = {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(it.imageUrl).decoderFactory(SvgDecoder.Factory()).crossfade(true).build(),
                            error = rememberVectorPainter(image = Icons.Filled.Lock),
                            contentDescription = "${it.title}",
                            modifier = Modifier
                                .height(50.dp)
                                .width(50.dp)
                        )
                    })
            }
        }

        NavigationDrawerItem(label = { Text("View All Games") }, selected = true, onClick = { },
            icon = {
                Icon(painter = painterResource(id = R.drawable.playing_cards), contentDescription = "",
                    modifier = Modifier
                    .height(50.dp)
                    .width(50.dp).scale(scaleX = 1f, scaleY = -1f))
            })
    }

}

@Composable
fun TcgDrawer_Header(onMenuIconClicked : () -> Unit)
{
    Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
        IconButton(onClick = { onMenuIconClicked() }, modifier = Modifier.padding(top = 10.dp,end = 10.dp)) {
            Image(imageVector = Icons.Filled.Menu, contentDescription = "Hamburger")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun Drawer_Preview()
{
    TcgDrawer()
}
