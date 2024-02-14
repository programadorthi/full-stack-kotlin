import androidx.compose.ui.window.ComposeUIViewController
import dev.programadorthi.full.stack.compose.app.ComposeApp
import platform.UIKit.UIViewController

public object MainViewController {

    public fun create(): UIViewController = ComposeUIViewController { ComposeApp() }

}