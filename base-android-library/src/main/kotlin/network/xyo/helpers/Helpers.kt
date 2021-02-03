package network.xyo.helpers

import android.os.Debug
import com.jaredrummler.android.device.DeviceName
import network.xyo.base.XYBase

val currentThreadName : String
    get() = Thread.currentThread().name

val deviceName: String
    get() = DeviceName.getDeviceName()

val hasDebugger: Boolean
    get() = Debug.isDebuggerConnected()

fun sourceNameFromAny(source: Any): String {
    return (source as? String) ?: classNameFromObject(source)
}

fun classNameFromObject(objectToCheck: Any): String {
    val parts = objectToCheck.javaClass.kotlin.simpleName?.split('.') ?: return "Unknown"
    return parts[parts.lastIndex]
}