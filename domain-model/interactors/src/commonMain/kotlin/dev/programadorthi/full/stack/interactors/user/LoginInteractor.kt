package dev.programadorthi.full.stack.interactors.user

import dev.programadorthi.models.user.BasicToken
import dev.programadorthi.models.user.Login
import dev.programadorthi.state.core.BaseValueManager
import dev.programadorthi.state.core.extension.basicValueManager
import dev.programadorthi.state.core.extension.plusAssign
import dev.programadorthi.state.validator.string.HasSizeValidator
import dev.programadorthi.state.validator.string.IsEqualToValidator
import dev.programadorthi.state.validator.string.IsNotBlankValidator

public class LoginInteractor(
    private val repository: LoginRepository,
) {
    public val username: BaseValueManager<String> = basicValueManager("")
    public val password: BaseValueManager<String> = basicValueManager("")

    init {
        username += IsNotBlankValidator(message = { "Username is required" })
        username += IsEqualToValidator(
            other = "fullstack",
            message = { "Username '$it' must be equals to fullstack" },
        )

        password += IsNotBlankValidator(message = { "Password is required" })
        password += HasSizeValidator(
            minSize = 6,
            maxSize = 6,
            message = { "Password length must be equals to 6" }
        )
        password += IsEqualToValidator(
            other = "kotlin",
            message = { "Password must be equals to 'kotlin'" },
        )
    }

    public suspend fun login(): Result<BasicToken> = runCatching {
        check(username.validate() && password.validate()) {
            username.messages.firstOrNull() ?: password.messages.first()
        }
        val login = Login(username = username.value, password = password.value)
        repository.doLogin(login)
    }
}