package com.devsa.notify_app.framework

import android.app.AlertDialog


class AlertDialogHandler {
    companion object {
        fun alertOk(message: String, title: String? = null, onOK: FunctionBlock = {}) {
            AlertDialog.Builder(G.currentActivity)
                .setMessage(message)
                .setTitle(title)
                .setPositiveButton("OK") { d, i -> onOK() }
                .create()
                .show()
        }

        fun alertOkCancel(
            message: String, title: String? = null,
            onOK: FunctionBlock = {},
            onCancel: FunctionBlock = {}
        ) {
            AlertDialog.Builder(G.currentActivity)
                .setMessage(message)
                .setTitle(title)
                .setPositiveButton("OK") { d, i -> onOK() }
                .setNeutralButton("Cancel") { d, i -> onCancel() }
                .create()
                .show()
        }

        fun alertYesNo(
            message: String, title: String? = null,
            onYes: FunctionBlock = {},
            onNo: FunctionBlock = {}
        ) {
            AlertDialog.Builder(G.currentActivity)
                .setMessage(message)
                .setTitle(title)
                .setPositiveButton("YES") { _, _ -> onYes() }
                .setNegativeButton("NO") { _, _ -> onNo() }
                .create()
                .show()
        }

        fun alertYesNoCancel(
            message: String, title: String? = null,
            onYes: FunctionBlock = {},
            onNo: FunctionBlock = {},
            onCancel: FunctionBlock = {}
        ) {
            AlertDialog.Builder(G.currentActivity)
                .setMessage(message)
                .setTitle(title)
                .setPositiveButton("YES") { d, i -> onYes() }
                .setNegativeButton("NO") { d, i -> onNo() }
                .setNeutralButton("Cancel") { d, i -> onCancel() }
                .create()
                .show()
        }
    }
}