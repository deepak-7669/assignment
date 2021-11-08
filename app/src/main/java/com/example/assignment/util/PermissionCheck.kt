package com.example.assignment.util

import android.app.Activity
import android.app.role.RoleManager
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.telecom.TelecomManager
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment


class PermissionCheck(val activity: Activity) : AppCompatActivity() {
    val permissionlist: MutableList<String> = ArrayList()

    @RequiresApi(Build.VERSION_CODES.M)
    fun checkPermissions(
            permissionArray: ArrayList<String>,
            wantToAskPermission: Boolean,
            requestCode: Int
    ): Boolean {
        for (i in permissionArray.indices) {
            if (ContextCompat.checkSelfPermission(activity, permissionArray[i])
                    != PackageManager.PERMISSION_GRANTED
            ) {
                permissionlist.add(
                        permissionArray[i]
                )
            }
        }
        return if (wantToAskPermission && permissionlist.isNotEmpty()) {
            ActivityCompat.requestPermissions(
                    activity,
                    permissionlist.toTypedArray(),
                    requestCode
            )
            false
        } else {
            permissionlist.isEmpty()
        }
    }

    fun checkPermissions(
            permissionArray: ArrayList<String>,
            wantToAskPermission: Boolean,
            requestCode: Int,
            isFragment: Boolean, fragment: Fragment
    ): Boolean {
        for (i in permissionArray.indices) {
            if (ContextCompat.checkSelfPermission(activity, permissionArray[i])
                    != PackageManager.PERMISSION_GRANTED
            ) {
                permissionlist.add(
                        permissionArray[i]
                )
            }
        }
        return if (wantToAskPermission && permissionlist.isNotEmpty()) {
            if (isFragment)
                fragment!!.requestPermissions(permissionlist.toTypedArray(), requestCode)
            else ActivityCompat.requestPermissions(
                    activity,
                    permissionlist.toTypedArray(),
                    requestCode
            )
            false
        } else {
            permissionlist.isEmpty()
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun requestChangeDialerPermission(REQUEST_CODE_SET_DEFAULT_DIALER: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val roleManager = getSystemService(ROLE_SERVICE) as RoleManager
            val intent = roleManager.createRequestRoleIntent(RoleManager.ROLE_DIALER)
            activity.startActivityForResult(intent, REQUEST_CODE_SET_DEFAULT_DIALER)
        } else {
            offerReplacingDefaultDialer()
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun offerReplacingDefaultDialer() {
        if (getSystemService(TelecomManager::class.java).defaultDialerPackage != packageName) {
            Intent(TelecomManager.ACTION_CHANGE_DEFAULT_DIALER)
                    .putExtra(TelecomManager.EXTRA_CHANGE_DEFAULT_DIALER_PACKAGE_NAME, packageName)
                    .let(::startActivity)
        }
    }

    fun isDefaultDialer(packageNameToCheck: String = activity.packageName): Boolean {
        val dialingIntent = Intent(Intent.ACTION_DIAL).addCategory(Intent.CATEGORY_DEFAULT)
        val resolveInfoList = activity.packageManager.queryIntentActivities(dialingIntent, 0)
        if (resolveInfoList.size != 1)
            return false
        return packageNameToCheck == resolveInfoList[0].activityInfo.packageName
    }
}


