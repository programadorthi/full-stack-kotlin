package dev.programadorthi.full.stack.compose.app.user

import dev.programadorthi.full.stack.compose.app.repository.LoginRepositoryImpl
import dev.programadorthi.full.stack.interactors.user.LoginInteractor
import dev.programadorthi.state.core.BaseValueManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

internal class LoginViewModel(
    private val scope: CoroutineScope // Best practice is pass context instead
) {
    private val interactor = LoginInteractor(LoginRepositoryImpl())

    val username: BaseValueManager<String> = interactor.username

    val password: BaseValueManager<String> = interactor.password

    fun login() {
        scope.launch {
            interactor.login()
                .onSuccess {
                    // save the token and navigate
                    println(">>> token: $it")
                }
                .onFailure { error ->
                    // report the exception
                    println("=================================================")
                    println(error)
                    println("=================================================")
                }
        }
    }
}