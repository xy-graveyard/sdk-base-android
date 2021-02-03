package network.xyo.base

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import network.xyo.info.XYInfo
import network.xyo.logging.XYLogging

open class XYBase {

    val now: Long
        get() {
            return System.currentTimeMillis()
        }

    val nowNano: Long
        get() {
            return System.nanoTime()
        }

    //we just-in-time create this as to not create it on objects that don't need it
    private var _log: XYLogging? = null
    val log: XYLogging
        get() {
            synchronized(this) {
                if (_log == null) {
                    _log = XYLogging(className())
                }
                return _log ?: throw NullPointerException()
            }
        }

    val className: String
        get() {
            return classNameFromObject(this)
        }

    fun getActivity(context: Context): Activity? {
        var contextToCheck = context
        while (contextToCheck is ContextWrapper) {
            if (contextToCheck is Activity) {
                return contextToCheck
            }
            contextToCheck = contextToCheck.baseContext
        }
        return null
    }

    fun className(): String {
        return classNameFromObject(this)
    }

    companion object : XYInfo() {

        fun log(source: String): XYLogging {
            return XYLogging(source)
        }
    }
}