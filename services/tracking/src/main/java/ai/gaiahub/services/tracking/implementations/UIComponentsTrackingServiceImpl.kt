package ai.gaiahub.services.tracking.implementations

import ai.gaiahub.core.domain.services.tracking.TrackingExecutor
import ai.gaiahub.core.domain.services.tracking.contracts.UIComponentsTrackingService
import ai.gaiahub.services.tracking.events.uicomponents.ButtonClickEventBuilderImpl
import ai.gaiahub.core.domain.services.tracking.models.TrackingResult

class UIComponentsTrackingServiceImpl(
    private val executor: TrackingExecutor
) : UIComponentsTrackingService {

    override suspend fun trackButtonClick(): TrackingResult {
        val builder = ButtonClickEventBuilderImpl()
        val event = builder.build()
        return executor.executeTracking(event = event)
    }
}