package ai.gaiahub.tracking.providers

import ai.gaiahub.tracking.events.TrackingEvent

interface TrackingProvider {
    val type: ProviderType
    suspend fun trackEvent(event: TrackingEvent): Result<Unit>
}