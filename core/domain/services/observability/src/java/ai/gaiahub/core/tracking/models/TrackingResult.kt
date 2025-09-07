package ai.gaiahub.core.tracking.models

import ai.gaiahub.core.models.tracking.ProviderType

/**
 * Represents the outcome of a tracking operation across multiple providers.
 *
 * Provides type-safe handling of tracking results with exhaustive pattern matching.
 * Aggregates outcomes from all configured providers into a single result.
 *
 * ## Example Usage
 * ```kotlin
 * when (val result = trackingService.trackLogin(email, success, error)) {
 *     is TrackingResult.Success -> {
 *         // All providers succeeded
 *     }
 *     is TrackingResult.Error -> {
 *         logger.error("Tracking failed: ${result.exception.message}")
 *     }
 *     is TrackingResult.PartialSuccess -> {
 *         // Some providers succeeded, some failed
 *         logger.warn("${result.failedProviders.size} providers failed")
 *     }
 * }
 * ```
 *
 * @see ai.gaiahub.core.tracking.contracts.LoyaltyTrackingService
 * @see ai.gaiahub.core.tracking.TrackingExecutor
 */
sealed class TrackingResult {

    /**
     * All targeted providers successfully processed the event.
     */
    object Success : TrackingResult()

    /**
     * All targeted providers failed to process the event.
     *
     * @property exception The error that caused the failure
     * @property provider The name of the provider that generated this error
     */
    data class Error(val exception: Throwable, val provider: ProviderType) : TrackingResult()

    /**
     * Mixed results where some providers succeeded and others failed.
     *
     * @property successfulProviders List of provider names that successfully tracked the event
     * @property failedProviders Map of provider names to their respective failure exceptions
     */
    data class PartialSuccess(
        val successfulProviders: List<ProviderType>,
        val failedProviders: Map<ProviderType, Throwable>
    ) : TrackingResult()
}