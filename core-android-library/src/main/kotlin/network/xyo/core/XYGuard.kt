package network.xyo.core

//this allows us to use guard to check for null on these object and
//conditionally execute code if they are null and returns a non-optional object
inline fun <T> T.guard(block: T.() -> Unit): T {
    if (this == null) block(); return this
}