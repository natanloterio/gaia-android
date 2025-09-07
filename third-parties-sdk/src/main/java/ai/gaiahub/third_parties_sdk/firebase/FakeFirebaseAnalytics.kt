package ai.gaiahub.third_parties_sdk.firebase

import android.os.Bundle
import android.util.Log

class FakeFirebaseAnalytics {

    fun logEvent(name: String, bundle: Bundle) {
        Log.d("FakeFirebaseAnalytics", "logEvent: $name - ${bundle.size()}")
    }
}