package dev.programadorthi.full.stack.compose.app.user

import dev.programadorthi.full.stack.compose.app.UIState
import dev.programadorthi.full.stack.compose.app.repository.LoginRepositoryImpl
import dev.programadorthi.full.stack.interactors.user.LoginInteractor
import dev.programadorthi.models.business.BusinessException
import dev.programadorthi.state.core.BaseValueManager
import dev.programadorthi.state.core.extension.basicValueManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

internal class LoginViewModel(
    private val scope: CoroutineScope // Best practice is pass context instead
) {
    private val interactor = LoginInteractor(LoginRepositoryImpl())

    val username: BaseValueManager<String> = interactor.username

    val password: BaseValueManager<String> = interactor.password

    val state: BaseValueManager<UIState<Unit>> = basicValueManager(UIState())

    fun login() {
        state.value = UIState.loading()
        scope.launch {
            interactor.login()
                .onSuccess {
                    delay(1_000) // taking time to show a loading animation
                    state.value = UIState.data(Unit)
                }
                .onFailure { error ->
                    // Avoiding show validated messages
                    state.value = UIState(
                        error = when (error) {
                            is BusinessException -> null
                            else -> error
                        }
                    )
                }
        }
    }
}