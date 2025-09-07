package ai.gaiahub.core.tracking.providers

import android.os.Bundle
import ai.gaiahub.core.models.tracking.ProviderType
import ai.gaiahub.core.tracking.consent.ConsentStatus
import ai.gaiahub.core.tracking.consent.ConsentType

interface AnalyticsWrapper {
    val type: ProviderType
    fun logEvent(name: String, parameters: Bundle?)
    fun setEnabled(enabled: Boolean)
    fun setConsent(consentSettings: Map<ConsentType, ConsentStatus>)
    fun setAdvertisingConsent(consent: ConsentStatus)
    fun setUserProperty(var1: String, var2: String?)
    fun setUserId(userId: String?)
}