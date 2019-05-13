package network.xyo.base.sample

import android.app.Activity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_xyo_base_sample.*
import network.xyo.base.XYBase
import network.xyo.base.XYInfo
import network.xyo.base.XYRandom

class XYOBaseSampleActivity : Activity() {

    private val info = XYInfo()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_xyo_base_sample)
        initDeviceName()
        initTimeFields()
        initTestLoggingButton()
        initActivityString()
        initRandom()
    }

    private fun initDeviceName() {
        txtDeviceName.text = getString(R.string.device_name_label, info.deviceName)
    }

    private fun initRandom() {
        txtRandom.text = getString(R.string.random_label, XYRandom.generateRandomBase62String(12))
    }

    private fun initTimeFields() {
        txtNow.text = getString(R.string.now_label, now)
        txtNowNano.text = getString(R.string.now_nano_label, nowNano)
    }

    private fun initActivityString() {
        txtActivity.text = getActivity(this).toString()
    }

    private fun initTestLoggingButton() {
        btnTestLogging.setOnClickListener {
            XYBase.log(className).info("==== Testing Logging [XYBase] ====")
                    .info("Log.info Test")
                    .info("initTestLoggingButton", "Log.info Test")
                    .error(Exception("XYOBaseSampleActivity Test Exception (Log.error)"))
                    .error("XYOBaseSampleActivity Test Exception (Log.error)")
                    .error(Exception("XYOBaseSampleActivity Test Exception (Log.error)"), false)
                    .action("Log.action Test")
                    .error("Log.error Test", false)
                    .status("Log.status Test")
        }
    }

    companion object : XYBase() {
        init {
            log.status("XYOBaseSampleActivity Companion Loaded")
        }
    }
}
