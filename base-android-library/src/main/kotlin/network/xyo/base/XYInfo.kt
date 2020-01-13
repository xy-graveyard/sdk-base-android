package network.xyo.base

import android.os.Debug
import com.jaredrummler.android.device.DeviceName

open class XYInfo {
    fun classNameFromObject(objectToCheck: Any): String {
        val parts = objectToCheck.javaClass.kotlin.simpleName?.split('.') ?: return "Unknown"
        return parts[parts.lastIndex]
    }

    fun sourceNameFromAny(source: Any): String {
        return (source as? String) ?: classNameFromObject(source)
    }

    val currentThreadName : String
        get() = Thread.currentThread().name

    val deviceName: String
        get() {
            try {
                return DeviceName.getDeviceName()
            } catch (ex: Exception) {
                XYBase.log("XYInfo").error("Include 'com.jaredrummler:android-device-names:1.x.x' to get device name")
            }
            return "Android"
        }

    val hasDebugger: Boolean
        get() = Debug.isDebuggerConnected()
}