package dev.programadorthi.full.stack.compose.app

internal data class UIState<out T>(
    val data: T? = null,
    val error: Throwable? = null,
    val isLoading: Boolean = false,
) {
    companion object {

        internal fun <T> data(data: T): UIState<T> = UIState(data = data)

        internal fun error(error: Throwable): UIState<Nothing> = UIState(error = error)

        internal fun loading(): UIState<Nothing> = UIState(isLoading = true)

    }
}
