package dev.programadorthi.full.stack.interactors.user

import dev.programadorthi.models.user.BasicToken
import dev.programadorthi.models.user.Login

public interface LoginRepository {
    public fun doLogin(login: Login): BasicToken
}