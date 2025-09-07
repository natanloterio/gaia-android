package ai.gaiahub.services.tracking.providers

import ai.gaiahub.core.domain.services.tracking.providers.ProviderType

enum class ProviderTypeImpl(override val displayName: String) : ProviderType {
    FIREBASE("firebase"),
    CRASHLYTICS("crashlytics"),
}