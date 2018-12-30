package network.xyo.core

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.res.Resources
import android.util.TypedValue
import android.view.View

//this allows us to use guard to check for null on these object and
//conditionally execute code if they are null and returns a non-optional object
inline fun <T> T.guard(block: T.() -> Unit): T {
    if (this == null) block(); return this
}

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
                    _log = XYLogging(this.tag)
                }
                return _log!!
            }
        }

    val tag: String
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

        val info = XYInfo()

        fun log(source: String): XYLogging {
            return XYLogging(source)
        }
    }

}