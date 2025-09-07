package ai.gaiahub.services.tracking.providers.firebase

import android.os.Bundle
import ai.gaiahub.third_parties_sdk.firebase.FakeFirebaseAnalytics
import ai.gaiahub.core.domain.services.tracking.events.TrackingEvent
import ai.gaiahub.core.domain.services.tracking.events.login.LoginEvent
import ai.gaiahub.core.domain.services.tracking.events.uicomponents.ButtonClickEvent
import ai.gaiahub.core.domain.services.tracking.providers.ProviderType
import ai.gaiahub.services.tracking.providers.ProviderTypeImpl
import ai.gaiahub.core.domain.services.tracking.providers.TrackingProvider

class FirebaseTrackingProvider(
        private val firebaseAnalytics: FakeFirebaseAnalytics
) : TrackingProvider {

    override val type: ProviderType = ProviderTypeImpl.FIREBASE

    override suspend fun trackEvent(event: TrackingEvent): Result<Unit> {
        return try {
            val bundle = Bundle().apply {
                event.parameters.forEach { (key, value) ->
                    when (value) {
                        is String -> putString(key.field, value)
                        is Int -> putInt(key.field, value)
                        is Long -> putLong(key.field, value)
                        is Double -> putDouble(key.field, value)
                        is Boolean -> putBoolean(key.field, value)
                        else -> putString(key.field, value.toString())
                    }
                }
            }

            when (event) {
                is LoginEvent -> {
                    firebaseAnalytics.logEvent(event.name.value, bundle)
                }

                is ButtonClickEvent -> firebaseAnalytics.logEvent(event.name.value, bundle)
            }

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}