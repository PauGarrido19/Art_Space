package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    ArtSpaceApp()
                }
            }
        }
    }
}

data class Artwork(
    @DrawableRes val image: Int,
    val title: String,
    val artist: String,
    val year: String
)

@Composable
fun ArtSpaceApp(modifier: Modifier = Modifier) {
    val artworks = listOf(
        Artwork(R.drawable.img1, "Título 1", "Artista 1", "2020"),
        Artwork(R.drawable.img2, "Título 2", "Artista 2", "2021"),
        Artwork(R.drawable.img3, "Título 3", "Artista 3", "2022"),
        Artwork(R.drawable.img4, "Título 4", "Artista 4", "2020"),
        Artwork(R.drawable.img5, "Título 5", "Artista 5", "2020")
    )

    var current by remember { mutableStateOf(0) }
    val artwork = artworks[current]

    Column(
        modifier = modifier
            .fillMaxSize()
            .safeDrawingPadding()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            ArtworkFrame(artwork)
        }

        Spacer(Modifier.height(24.dp))

        ArtworkInfo(artwork)

        Spacer(Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = {
                    current = if (current == 0) artworks.lastIndex else current - 1
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp)
            ) { Text("Previous") }

            Button(
                onClick = {
                    current = if (current == artworks.lastIndex) 0 else current + 1
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp)
            ) { Text("Next") }
        }
    }
}

@Composable
fun ArtworkFrame(artwork: Artwork, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier,
        shadowElevation = 8.dp,
        color = Color.White
    ) {
        Image(
            painter = painterResource(artwork.image),
            contentDescription = "${artwork.title} de ${artwork.artist}",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .padding(32.dp)
                .heightIn(max = 400.dp)
        )
    }
}

@Composable
fun ArtworkInfo(artwork: Artwork, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = Color(0xFFECEBF4),
        shape = RoundedCornerShape(0.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = artwork.title,
                fontSize = 24.sp,
                fontWeight = FontWeight.Light
            )
            Text(
                text = buildAnnotatedString {
                    withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                        append(artwork.artist)
                    }
                    append(" (${artwork.year})")
                }
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ArtSpaceAppPreview() {
    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            ArtSpaceApp()
        }
    }
}