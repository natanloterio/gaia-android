package ai.gaiahub.tracking.routes

import ai.gaiahub.tracking.events.TrackingEvent
import ai.gaiahub.tracking.providers.ProviderType
import kotlin.reflect.KClass

internal class TrackingProviderRouterImpl(
    private val eventProviderMapping: Map<KClass<out TrackingEvent>, Set<ProviderType>>
) : TrackingProviderRouter {

    override fun getProviderTypesForEvent(event: TrackingEvent): Set<ProviderType> {
        return eventProviderMapping[event::class] ?: emptySet()
    }
}
