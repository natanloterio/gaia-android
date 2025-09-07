package ai.gaiahub.third_parties_sdk.firebase

import android.util.Log

class FakeFirebaseCrashlytics {
    fun setCustomKey(string: String, name: Any?) {
        Log.d("FakeFirebaseCrashlytics", "setCustomKey: $string - $name")
    }
}