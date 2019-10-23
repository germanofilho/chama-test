package com.germanofilho.app.feature.home.presentation.view.activity

import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.View
import com.germanofilho.app.core.helper.PermissionHelper
import com.germanofilho.app.core.helper.observeResource
import com.germanofilho.app.core.view.BaseActivity
import com.germanofilho.app.feature.home.presentation.viewmodel.HomeViewModel
import com.germanofilho.chamaapp.R
import com.google.android.gms.location.LocationServices
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : BaseActivity(), IHomeView {

    private var lastLocation: Location? = null
    private val viewModel by viewModel<HomeViewModel>()

    private val fusedLocationClient by lazy { LocationServices.getFusedLocationProviderClient(this) }
    private val permissionHelper by lazy { PermissionHelper(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setupObservers()

        if(permissionHelper.permissionGranted()){
            setLastLocationListener()
        }
    }

    override fun onStart() {
        super.onStart()
        permissionHelper.checkPermissions()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            PermissionHelper.REQUEST_CODE -> if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                showSnackBar(getString(R.string.error_permission)) { permissionHelper.checkPermissions() }
            } else {
                setLastLocationListener()
            }
        }
    }

    private fun setLastLocationListener() {
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location : Location? ->
                location?.let {
                    setLastLocation(location)
                    initWhenLocationReady()
                }
            }
    }

    private fun showSnackBar(msg: String, action : () -> Unit) {
        Snackbar.make(parent_layout, msg, Snackbar.LENGTH_INDEFINITE)
            .setAction(getString(R.string.try_again)) {
                action.invoke()
            }.show()
    }

    override fun setupObservers() {
        viewModel.nearbyPlaces.observeResource(this,
            onSuccess = {
                Log.i("Success", it.toString())
            },
            onError = {
                showSnackBar(getString(R.string.error_service))
                { initWhenLocationReady() }
            },
            onLoading = { showLoading(it) }
        )
    }

    private fun setLastLocation(location: Location) {
        lastLocation = location
    }

    override fun initWhenLocationReady() {
        viewModel.getNearbyPlaces(lastLocation?.latitude, lastLocation?.longitude)
    }

    override fun showLoading(boolean: Boolean) {
        if(boolean) progress_bar.visibility = View.VISIBLE
        else progress_bar.visibility = View.GONE
    }

    override fun showError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadData() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
