import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import dev.programadorthi.full.stack.compose.app.ComposeApp

public fun main(): Unit = application {
    Window(onCloseRequest = ::exitApplication, title = "Fullstack Kotlin") {
        ComposeApp()
    }
}

@Preview
@Composable
internal fun AppDesktopPreview() {
    ComposeApp()
}