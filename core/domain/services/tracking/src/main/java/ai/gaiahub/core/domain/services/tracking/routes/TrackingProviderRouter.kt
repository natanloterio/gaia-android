package ai.gaiahub.core.domain.services.tracking.routes

import ai.gaiahub.core.domain.services.tracking.events.TrackingEvent
import ai.gaiahub.core.domain.services.tracking.providers.ProviderType

interface TrackingProviderRouter {
    fun getProviderTypesForEvent(event: TrackingEvent): Set<ProviderType>
}