package com.max.maxsamplekoin3.di

import com.max.maxsamplekoin3.model.data.User
import com.max.maxsamplekoin3.model.data.UserImpl
import com.max.maxsamplekoin3.model.repository.inventory.GetInventoryRepository
import com.max.maxsamplekoin3.model.repository.inventory.GetInventoryRepositoryImpl
import com.max.maxsamplekoin3.model.repository.login.LoginRepository
import com.max.maxsamplekoin3.model.repository.login.LoginRepositoryImpl
import com.max.maxsamplekoin3.model.usercase.inventory.GetInventoryUseCase
import com.max.maxsamplekoin3.model.usercase.inventory.GetInventoryUseCaseImpl
import com.max.maxsamplekoin3.model.usercase.login.LoginUseCase
import com.max.maxsamplekoin3.model.usercase.login.LoginUseCaseImpl
import org.koin.core.context.GlobalContext
import org.koin.core.definition.Definition
import org.koin.core.definition.OnCloseCallback
import org.koin.core.module.Module
import org.koin.core.qualifier.Qualifier
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope
import org.koin.dsl.module
import org.koin.dsl.onClose

private const val DomainLifecycleScopeId = "ConfigLifecycleScopeId"

val DomainLifecycleScope = named(DomainLifecycleScopeId)

fun getDomainScope(): Scope {
    return GlobalContext.get().getOrCreateScope(DomainLifecycleScopeId, DomainLifecycleScope)
}

fun closeDomainScope() {
    GlobalContext.get().getScopeOrNull(DomainLifecycleScopeId)?.close()
}

inline fun <reified T> Module.domainScoped(
    qualifier: Qualifier? = null,
    noinline definition: Definition<T>
) {
    factory<T>(qualifier) {
        getDomainScope().get(qualifier)
    }
    scope(DomainLifecycleScope) {
        scoped(qualifier, definition = definition)
    }
}

inline fun <reified T> Module.domainScopedClosable(
    qualifier: Qualifier? = null,
    noinline definition: Definition<T>,
    noinline onClose: OnCloseCallback<T>
) {
    factory<T>(qualifier) { getDomainScope().get(qualifier) }
    scope(DomainLifecycleScope) {
        scoped(qualifier, definition = definition) onClose (onClose)
    }
}

val domainModule = module {
    domainScoped<User> { UserImpl() }
}

val domainUserCaseModule = module {
    domainScoped<LoginUseCase> { LoginUseCaseImpl(get()) }
    domainScoped<GetInventoryUseCase> { GetInventoryUseCaseImpl(get()) }
}

val domainRepositoryModule = module {
    domainScoped<LoginRepository> { LoginRepositoryImpl(get(), get()) }
    domainScoped<GetInventoryRepository> { GetInventoryRepositoryImpl(get(), get()) }
}
