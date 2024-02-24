package dev.programadorthi.full.stack.compose.app.user

import dev.programadorthi.full.stack.interactors.user.LoginInteractor
import dev.programadorthi.full.stack.interactors.user.LoginRepository
import dev.programadorthi.models.user.BasicToken
import dev.programadorthi.models.user.Login
import dev.programadorthi.state.core.BaseValueManager

internal class LoginViewModel {
    private val interactor = LoginInteractor(object : LoginRepository {
        override fun doLogin(login: Login): BasicToken {
            TODO("Implement API call module")
        }
    })

    val username: BaseValueManager<String> = interactor.username

    val password: BaseValueManager<String> = interactor.password

    fun login() {
        interactor.login()
            .onSuccess {
                // save the token and navigate
            }
            .onFailure { error ->
                // report the exception
                println("=================================================")
                println(error)
                println("=================================================")
            }
    }
}