package network.xyo.base

import android.os.Debug
import com.jaredrummler.android.device.DeviceName

val currentThreadName : String
    get() = Thread.currentThread().name

val deviceName: String
    get() = DeviceName.getDeviceName()

val hasDebugger: Boolean
    get() = Debug.isDebuggerConnected()

internal fun classNameFromObject(objectToCheck: Any): String {
    val parts = objectToCheck.javaClass.kotlin.simpleName?.split('.') ?: return "Unknown"
    return parts[parts.lastIndex]
}