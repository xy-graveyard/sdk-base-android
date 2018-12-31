package network.xyo.core.sample

import android.app.Activity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_xyo_core_sample.*
import network.xyo.core.XYBase

class XYOCoreSampleActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_xyo_core_sample)
        initTestLoggingButton()
    }

    private fun initTestLoggingButton() {
        btnTestLogging.setOnClickListener {
            XYBase.log(TAG).info("==== Testing Logging [XYBase] ====")
                .info( "Log.info Test")
                .error(Exception("XYOCoreSampleActivity Test Exception (Log.error)"), false)
                .action("Log.action Test")
                .error("Log.error Test", false)
                .status("Log.status Test")
        }
    }

    companion object {

        private const val TAG = "XYOCoreSampleActivity"
    }
}
