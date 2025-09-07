package ai.gaiahub.app

import ai.gaiahub.di.DiConfig
import ai.gaiahub.di.useCasesModule
import ai.gaiahub.login.di.loginFeatureModule
import ai.gaiahub.services.tracking.di.trackingModule
import android.app.Application
import org.koin.core.context.GlobalContext

class GaiaApp : Application() {

    override fun onCreate() {
        super.onCreate()

        if (GlobalContext.getOrNull() == null) {
            DiConfig.initKoin(
                featureModules = listOf(
                    loginFeatureModule,
                    trackingModule,
                    useCasesModule
                )
            )
        }
    }
}