package ai.gaiahub.core.tracking.models

import ai.gaiahub.core.models.tracking.ProviderType

/**
 * Configuration for controlling which tracking providers are active in the system.
 *
 * Used by [com.hugoboss.core.tracking.TrackingExecutor] to filter available providers before executing
 * tracking operations.
 *
 * ## Example Usage
 * ```kotlin
 * val config = TrackingConfiguration(
 *     enabledProviders = setOf(
 *         ProviderTypeImpl.FIREBASE.displayName,
 *         ProviderTypeImpl.CRASHLYTICS.displayName
 *     )
 * )
 * ```
 *
 * @see ProviderType
 * @see ai.gaiahub.core.tracking.TrackingExecutor
 */
data class TrackingConfiguration(
    /**
     * Set of provider display names that should be active.
     * Uses [ProviderType] values. Empty set disables all providers.
     */
    val enabledProviders: Set<ProviderType> = emptySet()
)