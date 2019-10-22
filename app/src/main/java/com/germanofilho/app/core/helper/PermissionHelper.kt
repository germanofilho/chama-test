package com.germanofilho.app.core.helper

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat

class PermissionHelper(private val activity: Activity){

    companion object{
        val REQUEST_CODE = 1
    }

    fun checkPermissions() {
        if (isAndroidM()) {
            if (!permissionGranted()) {
                requestPermission()
            }
        }
    }

    fun permissionGranted(): Boolean{
        if(ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION)
            != PackageManager.PERMISSION_GRANTED){
            return false
        }

        return true
    }

    private fun requestPermission(){
        if(isAndroidM()){
            activity.requestPermissions(
                arrayOf(
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ), REQUEST_CODE
            )
        }
    }

    private fun isAndroidM(): Boolean{
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
    }

}