package network.xyo.base

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper

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
                    _log = XYLogging(info.classNameFromObject(this))
                }
                return _log ?: throw NullPointerException()
            }
        }

    val className: String
        get() {
            return info.classNameFromObject(this)
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

    companion object {

        //we just-in-time create this as to not create it on objects that don't need it
        private var pInfo: XYInfo? = null
        val info: XYInfo
            get() {
                synchronized(this) {
                    if (pInfo == null) {
                        pInfo = XYInfo()
                    }
                    return pInfo ?: throw NullPointerException()
                }
            }

        fun log(source: String): XYLogging {
            return XYLogging(source)
        }
    }
}