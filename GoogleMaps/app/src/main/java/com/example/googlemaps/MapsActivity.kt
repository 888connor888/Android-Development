package com.example.googlemaps

import android.annotation.SuppressLint
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.*

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import java.util.jar.Manifest

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    val locationManager by lazy {
        getSystemService(Context.LOCATION_SERVICE) as LocationManager
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onStart() {
        requestAccessFineLocation()
        super.onStart()
        when {
            isFineLocationGranted() -> {
                when{
                    isLocationEnabled() -> setUpLocationListener()
                    else -> showGpsNotEnabledDialog()
                }
            }
            else -> requestAccessFineLocation()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            999 -> if(grantResults.isNotEmpty() && grantResults[0] ==PackageManager.PERMISSION_GRANTED) {

                when{
                    isLocationEnabled() -> setUpLocationListener()
                    else -> showGpsNotEnabledDialog()
                }
            } else {
                Toast.makeText(this@MapsActivity,"Permission Not Granted",Toast.LENGTH_SHORT).show()
            }
        }
    }
    @SuppressLint("MissingPermission")
    private fun setUpLocationListener(){
        val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        val locationRequest = LocationRequest()
                .setInterval(2000) // milis
                .setFastestInterval(2000)
                .setSmallestDisplacement(1f)
                .setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY)
        fusedLocationProviderClient.requestLocationUpdates(locationRequest,
                object : LocationCallback(){
                    override fun onLocationResult(locationResult: LocationResult?) {
                        super.onLocationResult(locationResult)
                        for(locationResult in locationResult!!.locations){
                            val current = LatLng(locationResult.latitude,locationResult.longitude)
                            if(::mMap.isInitialized){
                                    mMap.addMarker(MarkerOptions().position(current).title("Current Location"))
                                    mMap.moveCamera(CameraUpdateFactory.newLatLng(current))
                            }
                        }
                    }
                },
        Looper.myLooper())
    }

    @SuppressLint("MissingPermission")
//    private fun setUpLocationListener() {
//        val providers = locationManager.getProviders(true)
//        var l:Location? = null
//        for(i in providers.indices.reversed()){
//            l = locationManager.getLastKnownLocation(providers[i])
//            if(l != null) break
//        }
//        l?.let {
//            if(::mMap.isInitialized){
//                val current = LatLng(it.latitude, it.longitude)
//                mMap.addMarker(MarkerOptions().position(current).title("Current Location"))
//                mMap.moveCamera(CameraUpdateFactory.newLatLng(current))
//            }
//        }
//    }

     fun isFineLocationGranted():Boolean{
         return checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
     }

    private fun requestAccessFineLocation(){
        this.requestPermissions(
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                999
        )
    }
    fun isLocationEnabled():Boolean{
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    }
    fun showGpsNotEnabledDialog(){
        AlertDialog.Builder(this)
                .setTitle("Enable GPS")
                .setMessage("GPS required for maps!")
                .setCancelable(false)
                .setPositiveButton("Enable Now"){ dialogInterface: DialogInterface, i: Int ->
                    startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
                    dialogInterface.dismiss()
                }.show()
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.uiSettings.apply {
            isZoomControlsEnabled = true
            isZoomGesturesEnabled = true
            isMyLocationButtonEnabled = true
            isRotateGesturesEnabled = true
            isCompassEnabled = true
        }
//        mMap.setMapStyle()
        mMap.setMaxZoomPreference(18f)

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
        mMap.addPolyline(PolylineOptions()
                .add(sydney,LatLng(20.59,78.39))
                .color(ContextCompat.getColor(baseContext,R.color.design_default_color_on_primary))).width = 2f
    }
}