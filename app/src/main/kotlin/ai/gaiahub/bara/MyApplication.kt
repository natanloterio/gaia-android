package ai.gaiahub.bara

import android.app.Application
import ai.gaiahub.di.DiConfig
import ai.gaiahub.di.useCasesModule
import ai.gaiahub.login.di.loginFeatureModule
import ai.gaiahub.tracking.di.trackingModule
import org.koin.core.context.GlobalContext

class MyApplication : Application() {

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