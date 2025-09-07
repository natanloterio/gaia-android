package ai.gaiahub.tracking.events.uicomponents

internal class ButtonClickEventBuilderImpl() : ButtonClickEventBuilder() {

    override suspend fun build(): ButtonClickEvent {
        val buttonClickEvent = ButtonClickEvent()

        return buttonClickEvent
    }
}