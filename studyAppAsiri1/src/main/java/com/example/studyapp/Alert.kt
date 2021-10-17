package com.example.studyapp

import android.app.Activity
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog

class Alert(activity: Activity, title: String, text: String) {

    init {
        var alert=AlertDialog.Builder(activity)

        alert.setTitle(title)
        alert.setMessage(text)
        alert.setPositiveButton("ok", DialogInterface.OnClickListener { dialogInterface, i ->dialogInterface.cancel()  })
        val A = alert.create()
        A.show()
    }}