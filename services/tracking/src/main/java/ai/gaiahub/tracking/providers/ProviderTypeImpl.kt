package ai.gaiahub.tracking.providers

enum class ProviderTypeImpl(override val displayName: String) : ProviderType {
    FIREBASE("firebase"),
    CRASHLYTICS("crashlytics"),
}