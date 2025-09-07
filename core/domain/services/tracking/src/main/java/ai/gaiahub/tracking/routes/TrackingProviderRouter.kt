package ai.gaiahub.tracking.routes

import ai.gaiahub.tracking.events.TrackingEvent
import ai.gaiahub.tracking.providers.ProviderType

interface TrackingProviderRouter {
    fun getProviderTypesForEvent(event: TrackingEvent): Set<ProviderType>
}