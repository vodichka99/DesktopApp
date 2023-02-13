import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.SwingPanel
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import uk.co.caprica.vlcj.factory.discovery.NativeDiscovery
//import uk.co.caprica.vlcj.player.base.MediaPlayer
import uk.co.caprica.vlcj.player.component.CallbackMediaPlayerComponent
//import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent
//import java.util.*

@Composable
internal fun VideoPlayerImpl(url: String, width: Int, height: Int) {
    NativeDiscovery().discover()
    val mediaPlayerComponent = remember {
        // see https://github.com/caprica/vlcj/issues/887#issuecomment-503288294 for why we're using CallbackMediaPlayerComponent for macOS.
//        if (isMacOS()) {
            CallbackMediaPlayerComponent()
//        } else {
//            EmbeddedMediaPlayerComponent()
//        }
//    }
//    SideEffect {
//        mediaPlayerComponent.mediaPlayer().media().play(url)
//    }
//
//    DisposableEffect(Unit) {
//        onDispose {
//            mediaPlayerComponent.mediaPlayer().release()
//        }
    }
    return SwingPanel(
        background = Color.Transparent,
        modifier = Modifier.fillMaxSize(),
        factory = {
            mediaPlayerComponent
        }
    )
}

@Composable
@Preview
fun App() {
    var text by remember { mutableStateOf("Hello, World!") }
//JFrame()
    MaterialTheme {
        Column(modifier = Modifier.fillMaxSize()) {
            Button(onClick = {
                text = "Hello, Desktop!"
            }) {
                Text(text)
            }
            VideoPlayerImpl(
                url = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
                width = 640,
                height = 480
            )
        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}