package network.xyo.base.sample

import android.app.Activity
import android.os.Bundle
import network.xyo.base.XYBase
import network.xyo.base.XYInfo
import network.xyo.base.XYRandom
import network.xyo.base.sample.databinding.ActivityXyoBaseSampleBinding

class XYOBaseSampleActivity : Activity() {

    private val info = XYInfo()

    private lateinit var binding: ActivityXyoBaseSampleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityXyoBaseSampleBinding.inflate(layoutInflater)

        setContentView(R.layout.activity_xyo_base_sample)
        initDeviceName()
        initTimeFields()
        initTestLoggingButton()
        initActivityString()
        initRandom()
    }

    private fun initDeviceName() {
        binding.txtDeviceName.text = getString(R.string.device_name_label, info.deviceName)
    }

    private fun initRandom() {
        binding.txtRandom.text = getString(R.string.random_label, XYRandom.generateRandomBase62String(12))
    }

    private fun initTimeFields() {
        binding.txtNow.text = getString(R.string.now_label, now)
        binding.txtNowNano.text = getString(R.string.now_nano_label, nowNano)
    }

    private fun initActivityString() {
        binding.txtActivity.text = getActivity(this).toString()
    }

    private fun initTestLoggingButton() {
        binding.btnTestLogging.setOnClickListener {
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
