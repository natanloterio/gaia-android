package ai.gaiahub.tracking.implementations

import ai.gaiahub.tracking.TrackingExecutor
import ai.gaiahub.tracking.contracts.UIComponentsTrackingService
import ai.gaiahub.tracking.events.uicomponents.ButtonClickEventBuilderImpl
import ai.gaiahub.tracking.models.TrackingResult

class UIComponentsTrackingServiceImpl(
    private val executor: TrackingExecutor
) : UIComponentsTrackingService {

    override suspend fun trackButtonClick(): TrackingResult {
        val builder = ButtonClickEventBuilderImpl()
        val event = builder.build()
        return executor.executeTracking(event = event)
    }
}