package ai.gaiahub.tracking.providers.crashlytics

import ai.gaiahub.third_parties_sdk.firebase.FakeFirebaseCrashlytics
import ai.gaiahub.tracking.events.TrackingEvent
import ai.gaiahub.tracking.providers.ProviderType
import ai.gaiahub.tracking.providers.ProviderTypeImpl
import ai.gaiahub.tracking.providers.TrackingProvider

class CrashlyticsTrackingProvider(
    private val crashlytics: FakeFirebaseCrashlytics
) : TrackingProvider {

    override val type: ProviderType = ProviderTypeImpl.CRASHLYTICS

    override suspend fun trackEvent(event: TrackingEvent): Result<Unit> {
        return try {
            crashlytics.setCustomKey("last_event", event.name)
            crashlytics.setCustomKey("last_event_timestamp", event.timestamp)

            event.parameters.forEach { (key, value) ->
                crashlytics.setCustomKey("event_$key", value.toString())
            }

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}