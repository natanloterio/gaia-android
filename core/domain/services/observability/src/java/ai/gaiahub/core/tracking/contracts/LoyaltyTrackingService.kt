package ai.gaiahub.core.tracking.contracts

/**
 * Main entry point for triggering tracking events from application modules.
 *
 * Provides high-level methods for tracking specific user actions and system events.
 * Each method creates the appropriate tracking event and submits it to the tracking system.
 *
 * ## Example Usage
 * ```kotlin
 * class AuthenticationComponent(
 *     private val trackingService: TrackingService
 * ) {
 *     suspend fun performLogin(email: String, password: String) {
 *         val result = authenticateUser(email, password)
 *
 *         trackingService.trackLogin(
 *             email = email,
 *             isSuccess = result.isSuccess,
 *             error = result.errorMessage
 *         )
 *     }
 * }
 * ```
 *
 * @see ai.gaiahub.core.tracking.models.TrackingResult
 * @see ai.gaiahub.core.tracking.TrackingExecutor
 */
interface LoyaltyTrackingService