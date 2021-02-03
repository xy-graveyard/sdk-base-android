package network.xyo.base

import java.util.*

class XYRandom : XYBase() {
    companion object {
        fun generateRandomBase62String(length: Int): String {
            val randomString = StringBuilder()
            val rand = Random(System.currentTimeMillis())
            for (x in 0 until length) {
                var rnd = rand.nextInt(62)
                rnd += when {
                    rnd < 10 -> 48
                    rnd < 36 -> 65 - 10
                    else -> 97 - 36
                }
                randomString.append(rnd.toChar())
            }
            return randomString.toString()
        }
    }
}
