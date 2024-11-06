package trpl.nim234311053.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import trpl.nim234311053.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Galeri(
                        name = "Bendu",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Galeri(name: String, modifier: Modifier = Modifier) {
    // Daftar gambar
    val images = listOf(
        painterResource(id = R.drawable.aap),
        painterResource(id = R.drawable.cristiano),
        painterResource(id = R.drawable.tijjani)  // Ganti dengan ID drawable ketiga
    )

    // State untuk menyimpan indeks gambar saat ini
    var currentIndex by remember { mutableStateOf(0) }

    // Menggunakan Box agar konten berada di tengah layar
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Menampilkan gambar sesuai dengan indeks saat ini
            Image(
                painter = images[currentIndex],
                contentDescription = "Sample Image ${currentIndex + 1}",
                modifier = Modifier
                    .size(200.dp)
                    .padding(bottom = 16.dp)
            )

            // Teks
            Text(
                text = "Hello $name!",
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Baris tombol Next dan Previous
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Tombol Previous
                Button(
                    onClick = { if (currentIndex > 0) currentIndex-- },
                    enabled = currentIndex > 0 // Disable jika di awal
                ) {
                    Text("Previous")
                }

                // Tombol Next
                Button(
                    onClick = { if (currentIndex < images.size - 1) currentIndex++ },
                    enabled = currentIndex < images.size - 1 // Disable jika di akhir
                ) {
                    Text("Next")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArtSpaceTheme {
        Galeri(name = "Android")
    }
}