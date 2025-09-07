package ai.gaiahub.services.tracking.routes

import ai.gaiahub.core.domain.services.tracking.events.TrackingEvent
import ai.gaiahub.core.domain.services.tracking.routes.TrackingProviderRouter
import ai.gaiahub.core.domain.services.tracking.providers.ProviderType
import kotlin.reflect.KClass

internal class TrackingProviderRouterImpl(
    private val eventProviderMapping: Map<KClass<out TrackingEvent>, Set<ProviderType>>
) : TrackingProviderRouter {

    override fun getProviderTypesForEvent(event: TrackingEvent): Set<ProviderType> {
        return eventProviderMapping[event::class] ?: emptySet()
    }
}
