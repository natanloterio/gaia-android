package ai.gaiahub.core.tracking.providers

import ai.gaiahub.core.models.tracking.ProviderType

interface CrashReportWrapper {
    val type: ProviderType
    fun setEnabled(isEnabled: Boolean)
    fun log(message: String)
    fun recordException(throwable: Throwable)
}