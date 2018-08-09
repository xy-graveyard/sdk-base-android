package network.xyo.core.sample

import android.app.Activity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_xyo_core_sample.*
import network.xyo.core.XYBase

class XYOCoreSampleActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        XYBase.init(this)
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_xyo_core_sample)

        btnTestLogging.setOnClickListener {
            XYBase.logError(TAG, "logError Test", false)
            XYBase.logException(TAG, Exception("XYOCoreSampleActivity Test Exception"), false)
            XYBase.logAction(TAG, "logAction Test")
            XYBase.logError(TAG, "logError", false)
            XYBase.logExtreme(TAG, "logExtreme Test")
            XYBase.logStatus(TAG, "logStatus Test")
        }

    }

    companion object {

        private const val TAG = "XYOCoreSampleActivity"
    }
}
