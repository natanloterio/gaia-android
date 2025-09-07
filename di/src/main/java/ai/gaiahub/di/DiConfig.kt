package ai.gaiahub.di

import org.koin.core.context.startKoin
import org.koin.core.module.Module

object DiConfig {
    fun initKoin(featureModules: List<Module> = emptyList()) {
        startKoin {
            modules(
                *featureModules.toTypedArray()
            )
        }
    }
}