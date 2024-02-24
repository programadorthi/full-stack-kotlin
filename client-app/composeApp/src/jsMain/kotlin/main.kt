import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.CanvasBasedWindow
import dev.programadorthi.full.stack.compose.app.ComposeApp

@OptIn(ExperimentalComposeUiApi::class)
public fun main() {
    CanvasBasedWindow(canvasElementId = "ComposeTarget") { ComposeApp() }
}