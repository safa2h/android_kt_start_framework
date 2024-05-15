package com.uncox.learn.notifyapp.framework

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.net.Uri
import android.provider.Settings
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.devsa.notify_app.framework.FunctionBlock
import com.devsa.notify_app.framework.G
import com.devsa.notify_app.framework.MethodBlock


class PermissionHandler(val permissions: List<String>, props: MethodBlock<PermissionHandler> = {}) {
    companion object {
        var lastRequestCode: Int = 0
    }

    private var activity = G.currentActivity
    private var requestCode: Int = ++lastRequestCode

    var onGrant: FunctionBlock? = null
    var permissionRequiredTitle: String = "Permission Required"
    var whyPermissionRequired: String = "Please grant all permissions..."

    init {
        activity.permissionHandlers.add(this)
        this.props()
        request()
    }


    private fun canShowReason(): Boolean {
        for (permission in permissions) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
                return true
            }
        }

        return false
    }


    private fun alert(message: String, onPositive: (DialogInterface, Int) -> Unit) {
        AlertDialog.Builder(activity)
            .setTitle(permissionRequiredTitle)
            .setMessage(message)
            .setPositiveButton("OK", onPositive)

            .create()
            .show()
    }

    private fun request() {
        if (isGranted()) {
            return
        }

        if (canShowReason()) {
            alert(whyPermissionRequired) { dialog, i ->
                requestPermission()
            }
        } else {
            requestPermission()
        }
    }

    private fun isGranted(): Boolean {
        for (permission in permissions) {
            if (PERMISSION_GRANTED != ContextCompat.checkSelfPermission(G.context, permission)) {
                return false
            }
        }

        return true
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            G.currentActivity,
            permissions.toTypedArray(),
            requestCode
        )
    }

    fun processOnPermissionResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ): Boolean {
        if (requestCode != this.requestCode) {
            return false
        }

        //it's mine
        if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
            if (onGrant != null) {
                onGrant!!.invoke()
            }

            return true
        }

        if (canShowReason()) {
            request()
            //is denied
        } else {
            //is always denied
            alert(whyPermissionRequired) { dialog, i ->

                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                val uri = Uri.fromParts("package", G.currentActivity.packageName, null)
                intent.data = uri
                ContextCompat.startActivity(G.currentActivity, intent, null)
            }
        }

        return true
    }
}