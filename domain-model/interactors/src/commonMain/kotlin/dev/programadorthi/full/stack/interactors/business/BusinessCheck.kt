package dev.programadorthi.full.stack.interactors.business

import dev.programadorthi.models.business.BusinessException
import dev.programadorthi.state.core.validation.ValidatorManager

@Throws(BusinessException::class)
public fun checkBusiness(vararg validatorManager: ValidatorManager<*>) {
    val validator = validatorManager
        .asSequence()
        .filter { it.validate().not() }
        .firstOrNull() ?: return
    throw BusinessException(message = validator.messages.first())
}