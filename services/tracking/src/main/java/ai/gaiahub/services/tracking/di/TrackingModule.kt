package ai.gaiahub.services.tracking.di

import ai.gaiahub.third_parties_sdk.firebase.FakeFirebaseAnalytics
import ai.gaiahub.third_parties_sdk.firebase.FakeFirebaseCrashlytics
import ai.gaiahub.services.tracking.TrackingExecutorImpl
import ai.gaiahub.core.domain.services.tracking.contracts.LoginTrackingService
import ai.gaiahub.services.tracking.implementations.LoginTrackingServiceImpl
import ai.gaiahub.core.domain.services.tracking.TrackingExecutor
import ai.gaiahub.core.domain.services.tracking.contracts.UIComponentsTrackingService
import ai.gaiahub.services.tracking.implementations.UIComponentsTrackingServiceImpl
import ai.gaiahub.core.domain.services.tracking.models.TrackingConfiguration
import ai.gaiahub.services.tracking.providers.ProviderTypeImpl
import ai.gaiahub.services.tracking.providers.crashlytics.CrashlyticsTrackingProvider
import ai.gaiahub.services.tracking.providers.firebase.FirebaseTrackingProvider
import ai.gaiahub.core.domain.services.tracking.providers.TrackingProvider
import ai.gaiahub.core.domain.services.tracking.routes.TrackingProviderRouter
import ai.gaiahub.services.tracking.routes.TrackingProviderRouterFactory
import org.koin.dsl.module

val trackingModule = module {

    single<TrackingConfiguration> {
        TrackingConfiguration(
            enabledProviders =
                setOf(ProviderTypeImpl.FIREBASE, ProviderTypeImpl.CRASHLYTICS)
        )
    }

    single<FakeFirebaseAnalytics> {
        FakeFirebaseAnalytics()
    }

    single<FakeFirebaseCrashlytics> {
        FakeFirebaseCrashlytics()
    }

    single<FirebaseTrackingProvider> {
        FirebaseTrackingProvider(
            firebaseAnalytics = get<FakeFirebaseAnalytics>()
        )
    }

    single<CrashlyticsTrackingProvider> {
        CrashlyticsTrackingProvider(
            crashlytics = get<FakeFirebaseCrashlytics>()
        )
    }

    single<List<TrackingProvider>> {
        listOf(
            get<FirebaseTrackingProvider>(),
            get<CrashlyticsTrackingProvider>()
        )
    }

    single<TrackingExecutor> {
        TrackingExecutorImpl(
            trackingProviders = get<List<TrackingProvider>>(),
            configuration = get<TrackingConfiguration>(),
            providerRouter = get<TrackingProviderRouter>()
        )
    }

    single<TrackingProviderRouter> {
        TrackingProviderRouterFactory.createDefaultRouter()
    }

    single<LoginTrackingService> {
        LoginTrackingServiceImpl(
            executor = get<TrackingExecutor>()
        )
    }

    single<UIComponentsTrackingService> {
        UIComponentsTrackingServiceImpl(
            executor = get<TrackingExecutor>()
        )
    }
}