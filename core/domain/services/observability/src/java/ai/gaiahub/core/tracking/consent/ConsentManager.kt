package ai.gaiahub.core.tracking.consent

interface ConsentManager {
    suspend fun migrateExistingConsent(isAnalyticsReportingEnabled: Boolean, isCrashReportingEnabled: Boolean, dialogOpen: Boolean)
}