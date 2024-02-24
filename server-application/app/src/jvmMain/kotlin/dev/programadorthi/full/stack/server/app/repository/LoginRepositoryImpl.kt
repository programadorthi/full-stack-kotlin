package dev.programadorthi.full.stack.server.app.repository

import dev.programadorthi.full.stack.interactors.user.LoginRepository
import dev.programadorthi.full.stack.server.app.plugins.hashedUserTable
import dev.programadorthi.models.user.BasicToken
import dev.programadorthi.models.user.Login
import io.ktor.server.auth.UserPasswordCredential
import io.ktor.util.encodeBase64

internal class LoginRepositoryImpl : LoginRepository {

    override suspend fun doLogin(login: Login): BasicToken {
        val credential = UserPasswordCredential(
            name = login.username,
            password = login.password
        )
        checkNotNull(hashedUserTable.authenticate(credential)) {
            "Invalid username or password"
        }
        val token = "${credential.name}:${credential.password}".encodeBase64()
        return BasicToken(basic = token)
    }

}