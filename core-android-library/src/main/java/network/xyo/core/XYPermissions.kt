package network.xyo.core

/*
Created by arietrouw on 1/31/17.
*/

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class XYPermissions(private val context: Context) : XYBase() {

    fun checkPermissionForGallery(): Boolean {
        val result = ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE)
        return (result == PackageManager.PERMISSION_GRANTED)
    }

    fun checkPermissionForCamera(): Boolean {
        val result = ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
        return (result == PackageManager.PERMISSION_GRANTED)
    }

    fun checkPermissionForBluetooth(): Boolean {
        val result = ContextCompat.checkSelfPermission(context, Manifest.permission.BLUETOOTH)
        return (result == PackageManager.PERMISSION_GRANTED)
    }

    fun checkPermissionForBluetoothAdmin(): Boolean {
        val result = ContextCompat.checkSelfPermission(context, Manifest.permission.BLUETOOTH_ADMIN)
        return (result == PackageManager.PERMISSION_GRANTED)
    }

    fun checkPermissionForBluetoothPrivilaged(): Boolean {
        val result = ContextCompat.checkSelfPermission(context, Manifest.permission.BLUETOOTH_PRIVILEGED)
        return (result == PackageManager.PERMISSION_GRANTED)
    }

    fun checkPermissionForCoarseLocation(): Boolean {
        val result = ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION)
        return (result == PackageManager.PERMISSION_GRANTED)
    }

    fun checkPermissionForFineLocation(): Boolean {
        val result = ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION)
        return (result == PackageManager.PERMISSION_GRANTED)
    }

    fun requestPermission(permission: String, explainText: String, reqCode: Int) {
        if (Build.VERSION.SDK_INT < 23) {
            return
        }

        if (checkPermissionForCoarseLocation() && checkPermissionForFineLocation()) {
            return
        }

        if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(context as Activity, permission)) {

                val alertDialog = AlertDialog.Builder(context).create()
                alertDialog.setTitle("Permission Needed")
                alertDialog.setMessage(explainText)
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK"
                ) { dialog, _ ->
                    dialog.dismiss()
                    ActivityCompat.requestPermissions(context,
                            arrayOf(permission),
                            reqCode)
                }
                if (!context.isFinishing) {
                    alertDialog.show()
                }
            } else {
                ActivityCompat.requestPermissions(context,
                        arrayOf(permission),
                        reqCode)
            }
        }
    }

    fun requestPermissions(permissions: Array<String>, explainText: String, reqCode: Int, granted: () -> Unit) {
        if (Build.VERSION.SDK_INT < 23) {
            granted()
            return
        }

        if (checkPermissionForCoarseLocation() && checkPermissionForFineLocation()) {
            granted()
            return
        }

        if (ContextCompat.checkSelfPermission(context, permissions[0])
                != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(context, permissions[1])
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(context as Activity, permissions[0])
                    && ActivityCompat.shouldShowRequestPermissionRationale(context, permissions[1])) {

                val alertDialog = AlertDialog.Builder(context).create()
                alertDialog.setTitle("Permission Needed")
                alertDialog.setMessage(explainText)
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK"
                ) { dialog, _ ->
                    dialog.dismiss()
                    ActivityCompat.requestPermissions(context,
                            permissions,
                            reqCode)
                    granted()
                }
                if (!context.isFinishing) {
                    alertDialog.show()
                }
            } else {
                ActivityCompat.requestPermissions(context,
                        permissions,
                        reqCode)
            }
        }
    }

    companion object {

        private val TAG = XYPermissions::class.java.simpleName
        const val GALLERY_PERMISSION_REQ_CODE = 109
        const val CAMERA_PERMISSION_REQ_CODE = 209
        const val LOCATION_PERMISSIONS_REQ_CODE = 309
        const val FINE_LOCATION_REQ_CODE = 310
        const val BLUETOOTH_PERMISSIONS_REQ_CODE = 311
    }
}
