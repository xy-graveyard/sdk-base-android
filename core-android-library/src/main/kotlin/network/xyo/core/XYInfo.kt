package network.xyo.core

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
        get() = DeviceName.getDeviceName()

    val hasDebugger: Boolean
        get() = Debug.isDebuggerConnected()
}