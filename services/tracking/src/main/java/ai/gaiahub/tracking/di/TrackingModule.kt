package ai.gaiahub.tracking.di

import ai.gaiahub.third_parties_sdk.firebase.FakeFirebaseAnalytics
import ai.gaiahub.third_parties_sdk.firebase.FakeFirebaseCrashlytics
import ai.gaiahub.tracking.TrackingExecutorImpl
import ai.gaiahub.tracking.contracts.LoginTrackingService
import ai.gaiahub.tracking.implementations.LoginTrackingServiceImpl
import ai.gaiahub.tracking.TrackingExecutor
import ai.gaiahub.tracking.contracts.UIComponentsTrackingService
import ai.gaiahub.tracking.implementations.UIComponentsTrackingServiceImpl
import ai.gaiahub.tracking.models.TrackingConfiguration
import ai.gaiahub.tracking.providers.ProviderTypeImpl
import ai.gaiahub.tracking.providers.crashlytics.CrashlyticsTrackingProvider
import ai.gaiahub.tracking.providers.firebase.FirebaseTrackingProvider
import ai.gaiahub.tracking.providers.TrackingProvider
import ai.gaiahub.tracking.routes.TrackingProviderRouter
import ai.gaiahub.tracking.routes.TrackingProviderRouterFactory
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